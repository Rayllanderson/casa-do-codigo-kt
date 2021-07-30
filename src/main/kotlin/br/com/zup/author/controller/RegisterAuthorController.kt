package br.com.zup.author.controller
import br.com.zup.author.model.Author
import br.com.zup.author.repositories.AuthorRepository
import br.com.zup.author.requests.AuthorPostRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.validation.Valid

@Validated
@Controller("/authors")
class RegisterAuthorController(
    val authorRepository: AuthorRepository
) {

    val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Post
    fun register(@Body @Valid request: AuthorPostRequest): HttpResponse<Any>{

        val author: Author = request.toAuthor()
        authorRepository.save(author)

        logger.info("Autor de email {} cadastrado as {} com id {}", author.email, author.registeredIn, author.id)

        val uri = UriBuilder.of("/authors/{id}").expand(mutableMapOf("id" to author.id))

        return HttpResponse.created(uri)
    }
}