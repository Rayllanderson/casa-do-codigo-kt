package br.com.zup.author.responses

import br.com.zup.author.model.Author
import com.fasterxml.jackson.annotation.JsonFormat

class AuthorDetailsResponse(author: Author) {
    val id = author.id
    val name = author.name
    val email = author.email
    val description = author.description
    @JsonFormat(pattern = "dd-MM-yyyy")
    val registeredIn = author.registeredIn
}