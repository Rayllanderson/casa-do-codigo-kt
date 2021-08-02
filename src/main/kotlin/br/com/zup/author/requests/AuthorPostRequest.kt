package br.com.zup.author.requests

import br.com.zup.author.model.Author
import br.com.zup.author.responses.AddressResponse
import br.com.zup.core.validators.Unique
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class AuthorPostRequest(
    @field:NotBlank
    val name: String,

    @field:Email
    @field:NotBlank
    @field:Unique(entity = Author::class, field = "email")
    val email: String,

    @field:NotBlank
    @field:Size(max = 400)
    val description: String,

    @field:NotBlank
    val addressNumber: String,

    @field:NotBlank
    val zipCode: String
){
    fun toAuthor(address: AddressResponse): Author{
        return Author(this.name, this.email, this.description, address.toAddress(addressNumber))
    }
}
