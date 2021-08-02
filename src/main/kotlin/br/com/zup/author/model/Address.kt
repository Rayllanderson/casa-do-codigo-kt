package br.com.zup.author.model

import javax.persistence.Embeddable

@Embeddable
class Address(
    val number: String,
    val zipCode : String,
    val district: String,
    val city: String,
    val state: String,
)