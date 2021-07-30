package br.com.zup.author.controller

import br.com.zup.author.model.Author
import br.com.zup.author.repositories.AuthorRepository
import br.com.zup.author.responses.AuthorDetailsResponse
import br.com.zup.author.responses.AuthorResponse
import br.com.zup.core.exceptions.ApiErrorException
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller("/authors")
class FindAuthorController(
    val authorRepository: AuthorRepository
) {

    val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Get
    fun find(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        if(email.isBlank()){
            return findAll()
        }
        return findByEmail(email)
    }

    @Get("/{id}")
    fun findById(@PathVariable id: Long): HttpResponse<AuthorDetailsResponse> {
        val author: Author = authorRepository.findById(id).orElseThrow {
            ApiErrorException(HttpStatus.NOT_FOUND, "Autor não encontrado")
        }

        logger.info("Autor {} foi consultado", author.email)

        return HttpResponse.ok(AuthorDetailsResponse(author))
    }

    private fun findByEmail(email: String): HttpResponse<Any> {
        val possibleAuthor = authorRepository.findByEmail(email)
        if (possibleAuthor.isEmpty) return HttpResponse.notFound()

        val author = possibleAuthor.get()

        logger.info("Autor {} foi consultado", author.email)

        return HttpResponse.ok(AuthorDetailsResponse(author))
    }

    private fun findAll(): HttpResponse<Any> {
        val authors = authorRepository.findAll()
        val response = authors.map { author ->
            AuthorResponse(author)
        }

        logger.info("Foram listados {} autores", authors.size)

        return HttpResponse.ok(response)
    }
}