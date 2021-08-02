package br.com.zup.author.controller

import br.com.zup.author.model.Address
import br.com.zup.author.model.Author
import br.com.zup.author.repositories.AuthorRepository
import br.com.zup.author.responses.AuthorDetailsResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class FindAuthorControllerTest{

    @Inject
    lateinit var authorRepository: AuthorRepository

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    private lateinit var author: Author

    @BeforeEach
    internal fun setUp(){
        val address = Address(
            "22AB2",
            "12568710",
            "whatever",
            "idk",
            "state of whatever"
        )
        author = Author("Kaguya sama", "kaguya@email.com", "love is war", address)
        authorRepository.save(author)
    }

    @AfterEach
    internal fun tearDown(){
        authorRepository.deleteAll()
    }

    @Test
    internal fun `Should find author when email is valid`(){
        val response = client.toBlocking().exchange(
            "/authors?email=${author.email}",
            AuthorDetailsResponse::class.java
        )

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(response.body()?.id, author.id)
        assertEquals(response.body()?.name, author.name)
        assertEquals(response.body()?.email, author.email)
        assertEquals(response.body()?.description, author.description)
    }
}