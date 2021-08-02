package br.com.zup.author.controller

import br.com.zup.author.clients.AddressConsultantClient
import br.com.zup.author.requests.AuthorPostRequest
import br.com.zup.author.responses.AddressResponse
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject

@MicronautTest
internal class RegisterAuthorControllerTest {

    @Inject
    lateinit var addressClient: AddressConsultantClient

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun `Should register a new Author`(){
        val zipCode = "13564556"
        val requestBody = AuthorPostRequest(
            "Kaguya sama",
            "kaguya@email.com",
            "love is war",
            "26",
            zipCode
        )

        Mockito.`when`(this.addressClient.consult(zipCode)).thenReturn(HttpResponse.ok(AddressResponse(
            zipCode,
            "whatever",
            "city of whatever",
            "state of whatever"
        )))

        val request = HttpRequest.POST("/authors", requestBody)
        val response = client.toBlocking().exchange(request, Any::class.java)

        assertThat(response.status).isEqualTo(HttpStatus.CREATED)
        assertThat(response.headers.contains(HttpHeaders.LOCATION)).isTrue
        print(response.header(HttpHeaders.LOCATION))
        assertThat(response.header(HttpHeaders.LOCATION)?.matches("/authors/\\d".toRegex())).isTrue
    }

    @MockBean(AddressConsultantClient::class)
    fun addressClientMock(): AddressConsultantClient{
        return Mockito.mock(AddressConsultantClient::class.java)
    }


}

