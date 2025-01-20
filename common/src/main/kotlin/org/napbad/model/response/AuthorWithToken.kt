package org.napbad.model.response

import org.napbad.model.entity.Author

class AuthorWithToken (
    val user: Author,
    val token: String
)