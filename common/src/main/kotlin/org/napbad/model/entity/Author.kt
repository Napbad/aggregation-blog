package org.napbad.model.entity

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.GeneratedValue
import org.babyfish.jimmer.sql.GenerationType
import java.time.LocalDateTime
import kotlin.time.TimeSource


@Entity
interface Author {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    val authorId: Long

    val authorName: String

    val contactInfo: String?

    val bio: String?

    val joinedTime: LocalDateTime?

    val email: String

    val githubLink: String?

    val weiboLink: String?

    val verified: Boolean

    val password: String
}