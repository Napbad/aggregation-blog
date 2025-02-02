package org.napbad.clientbackend.service

import org.babyfish.jimmer.Page
import org.napbad.model.dto.article.*

interface ArticleService {
    fun add(article: ArticleCreateInput): ArticleCreateOutput
    fun query(article: ArticleSpecification): Page<ArticleQueryOutput>
    fun update(article: ArticleUpdateInput): ArticleUpdateOutput
    fun delete(article: ArticleDeleteInput): String
}