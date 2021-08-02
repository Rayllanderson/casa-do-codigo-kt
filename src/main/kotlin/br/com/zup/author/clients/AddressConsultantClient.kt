package br.com.zup.author.clients

import br.com.zup.author.responses.AddressResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client("\${address.consultant.url}")
interface AddressConsultantClient {

    @Get("/{zipCode}/json/")
    fun consult(@PathVariable zipCode: String): HttpResponse<AddressResponse>

}