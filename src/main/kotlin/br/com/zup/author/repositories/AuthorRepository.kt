package br.com.zup.author.repositories

import br.com.zup.author.model.Author
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.Optional

@Repository
interface AuthorRepository: JpaRepository<Author, Long>{
    fun findByEmail(email: String): Optional<Author>
}