package org.napbad.model.entity

import org.babyfish.jimmer.sql.*
import kotlin.time.TimeSource


@Entity
@Table(name = "article")
interface Article{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    val articleId: Int

    val authorId: Int

    val title: String

    val content: String

    val category: String?

    val publishTime: TimeSource

    val views: Int

    val likes: Int

    val abstract: String?

    val copyrightInfo: String?

    val version: Int
}