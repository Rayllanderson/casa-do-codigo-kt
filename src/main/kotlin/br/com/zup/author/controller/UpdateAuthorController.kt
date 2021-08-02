package br.com.zup.author.controller

import br.com.zup.author.repositories.AuthorRepository
import br.com.zup.author.responses.AuthorDetailsResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Validated
@Controller("/authors/{id}")
class UpdateAuthorController(
    val authorRepository: AuthorRepository
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Put
    @Transactional
    fun update(@PathVariable id: Long, @NotBlank @Size(max = 400) description: String): HttpResponse<Any>{
        val possibleAuthor = authorRepository.findById(id)

        if (possibleAuthor.isEmpty) return HttpResponse.notFound()

        val author = possibleAuthor.get()

        author.description = description

        logger.info("Autor {} fez uma alteração na descrição", author.email)

        return HttpResponse.ok(AuthorDetailsResponse.fromAuthor(author))
    }

}