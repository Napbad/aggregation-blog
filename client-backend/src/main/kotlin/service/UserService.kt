package org.napbad.clientbackend.service

import org.napbad.model.dto.author.*
import org.napbad.model.entity.Author


interface UserService {

    fun register(author: AuthorRegisterInput): AuthorRegisterOutput
    fun login(user: AuthorLoginInput): AuthorLoginOutput
    fun update(user: AuthorUpdateInput): AuthorUpdateOutput

}