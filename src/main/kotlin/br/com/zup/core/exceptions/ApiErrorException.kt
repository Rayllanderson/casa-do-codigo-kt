package br.com.zup.core.exceptions

import io.micronaut.http.HttpStatus

class ApiErrorException(
    val status: HttpStatus,
    override val message: String
): RuntimeException()