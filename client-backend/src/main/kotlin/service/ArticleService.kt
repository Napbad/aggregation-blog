package org.napbad.clientbackend.service

import org.babyfish.jimmer.Page
import org.napbad.model.dto.article.ArticleCreateInput
import org.napbad.model.dto.article.ArticleCreateOutput
import org.napbad.model.dto.article.ArticleQueryOutput
import org.napbad.model.dto.article.ArticleSpecification

interface ArticleService {
    fun add(article: ArticleCreateInput): ArticleCreateOutput
    fun query(article: ArticleSpecification): Page<ArticleQueryOutput>
}