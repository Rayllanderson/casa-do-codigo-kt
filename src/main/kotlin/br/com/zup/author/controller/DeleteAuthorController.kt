package br.com.zup.author.controller

import br.com.zup.author.repositories.AuthorRepository
import br.com.zup.author.responses.AuthorDetailsResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Validated
@Controller("/authors/{id}")
class DeleteAuthorController(
    val authorRepository: AuthorRepository
) {
    val logger = LoggerFactory.getLogger(javaClass)

    @Delete
    fun delete(@PathVariable id: Long): HttpResponse<Any>{
        val possibleAuthor = authorRepository.findById(id)

        if (possibleAuthor.isEmpty) return HttpResponse.notFound()

        val author = possibleAuthor.get()

        authorRepository.deleteById(id)

        logger.info("Autor {} foi excluído", author.email)

        return HttpResponse.ok()
    }

}