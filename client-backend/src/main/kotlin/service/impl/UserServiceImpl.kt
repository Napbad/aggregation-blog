package org.napbad.clientbackend.service.impl

import lombok.extern.slf4j.Slf4j
import org.babyfish.jimmer.sql.JSqlClient
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.napbad.model.entity.Author
import org.napbad.model.entity.AuthorProps
import org.napbad.model.entity.authorId
import org.napbad.model.entity.email
import org.napbad.clientbackend.service.UserService
import org.napbad.expection.ErrorCode
import org.napbad.expection.ExceptionFactory
import org.napbad.model.dto.author.*
import org.springframework.stereotype.Service
import org.napbad.utilities.security.SHA256Encryption

@Slf4j
@Service
class UserServiceImpl(
    private val sqlClient: KSqlClient,
) : UserService {
    override fun register(author: AuthorRegisterInput): AuthorRegisterOutput {
        val existingAuthors = sqlClient.createQuery(Author::class) {
            where(table.email.eq(author.email))
            select(table)
        }.limit(10).execute()

        existingAuthors.forEach { authorIter ->
            run {
                if (authorIter.authorName == author.authorName) {
                    throw ExceptionFactory.exception(
                        ErrorCode.USER_ALREADY_EXIST,
                        "User already exist, user name is duplicated"
                    )
                }
                if (authorIter.email == author.email) {
                    throw ExceptionFactory.exception(
                        ErrorCode.USER_ALREADY_EXIST,
                        "User already exist, email is duplicated"
                    )
                }
            }
        }

        val authorSimpleSaveResult = sqlClient.insert(author)

        return AuthorRegisterOutput(authorSimpleSaveResult.modifiedEntity)
    }

    override fun login(user: AuthorLoginInput): AuthorLoginOutput {
        val authors = sqlClient.createQuery(
            Author::class
        ) {
            where(table.email.eq(user.email))
            select(table)
        }.limit(2).execute()

        if (authors.size != 1) {
            throw ExceptionFactory.exception(ErrorCode.UNKNOWN_ERROR)
        }

        if ((authors[0].password != user.password?.let { SHA256Encryption.getSHA256(it) })
            ) {
            throw ExceptionFactory.exception(ErrorCode.USERNAME_OR_PASSWORD_WRONG)
        }

        return AuthorLoginOutput(authors[0])
    }

    override fun update(user: AuthorUpdateInput): AuthorUpdateOutput {
        val saveResult = sqlClient.save(user)
        return AuthorUpdateOutput(saveResult.modifiedEntity)
    }
}