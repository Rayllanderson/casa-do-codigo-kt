package br.com.zup.author.controller

import br.com.zup.author.repositories.AuthorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.transaction.Transactional

@Validated
@Controller("/authors/{id}")
class DeleteAuthorController(
    val authorRepository: AuthorRepository
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Delete
    @Transactional
    fun delete(@PathVariable id: Long): HttpResponse<Any>{
        val possibleAuthor = authorRepository.findById(id)

        if (possibleAuthor.isEmpty) return HttpResponse.notFound()

        val author = possibleAuthor.get()

        authorRepository.deleteById(id)

        logger.info("Autor {} foi excluído", author.email)

        return HttpResponse.ok()
    }

}