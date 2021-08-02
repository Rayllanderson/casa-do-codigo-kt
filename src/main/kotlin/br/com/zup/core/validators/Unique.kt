package br.com.zup.core.validators

import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CONSTRUCTOR
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass

@MustBeDocumented
@Target(FIELD, CONSTRUCTOR)
@Retention(RUNTIME)
@Constraint(validatedBy = [UniqueValidator::class])
annotation class Unique(
    val message: String = "Email deve ser único",
    val entity: KClass<*>,
    val field: String,
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

@Singleton
open class UniqueValidator(
    private val manager: EntityManager
): ConstraintValidator<Unique, String> {

    private lateinit var entity: KClass<*>
    private lateinit var field: String

    override fun initialize(annotation: Unique?) {
        this.entity = annotation!!.entity
        this.field = annotation.field
    }

    @Transactional
    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        if(value.isNullOrBlank()) return true
        return query(value).resultList.isEmpty()
    }

    private fun query(value: String?) =
        manager.createQuery("SELECT 1 FROM ${entity.qualifiedName} e WHERE e.${field} = '${value}'")
}

