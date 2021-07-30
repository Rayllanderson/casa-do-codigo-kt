package br.com.zup.author.responses

import br.com.zup.author.model.Author

class AuthorResponse(author: Author) {
    val name = author.name
    val email = author.email
    val description = author.description
}