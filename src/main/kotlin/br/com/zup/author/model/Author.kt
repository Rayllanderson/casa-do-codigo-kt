package br.com.zup.author.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@Entity
class Author (

    @field:NotBlank
    @field:Column(nullable = false)
    val name: String,

    @field:Email @field:NotBlank
    @field:Column(nullable = false)
    val email: String,

    @field:NotBlank @field:Size(max = 400)
    @field:Column(nullable = false)
    var description: String,

    @field:Embedded
    @field:NotNull
    val address: Address
){
    @Id
    @GeneratedValue
    var id: Long? = null
        private set

    @field:NotNull
    @field:Column(nullable = false)
    val registeredIn: LocalDate = LocalDate.now()
}