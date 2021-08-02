package br.com.zup.author.responses

import br.com.zup.author.model.Address
import com.fasterxml.jackson.annotation.JsonProperty

class AddressResponse(
    @JsonProperty("cep")
    val zipCode: String,
    @JsonProperty("bairro")
    val district: String,
    @JsonProperty("localidade")
    val city: String,
    @JsonProperty("uf")
    val state: String
){
    fun toAddress(number: String): Address{
        return Address(number, zipCode, district, city, state)
    }
}
