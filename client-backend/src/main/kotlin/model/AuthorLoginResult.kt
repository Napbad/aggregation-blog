package model

import org.napbad.model.dto.author.AuthorLoginOutput

data class AuthorLoginResult (
    val authorLoginOutput: AuthorLoginOutput,
    val msg: String
)
