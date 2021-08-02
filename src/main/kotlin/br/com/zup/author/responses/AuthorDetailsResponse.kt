package br.com.zup.author.responses

import br.com.zup.author.model.Author
import com.fasterxml.jackson.annotation.JsonFormat
import io.micronaut.core.annotation.Introspected
import java.time.LocalDate

@Introspected
data class AuthorDetailsResponse(
    val id: Long?,
    val name: String,
    val email: String,
    val description: String,
    val registeredIn: LocalDate,
){
    companion object{
        fun fromAuthor(author: Author): AuthorDetailsResponse{
            return AuthorDetailsResponse(author.id, author.name, author.email, author.description, author.registeredIn)
        }
    }
}