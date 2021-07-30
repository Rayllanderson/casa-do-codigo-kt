package br.com.zup.author.requests

import br.com.zup.author.model.Author
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
    val email: String,

    @field:NotBlank
    @field:Size(max = 400)
    val description: String
){
    fun toAuthor(): Author{
        return Author(this.name, this.email, this.description)
    }
}
