package org.napbad.clientbackend.service.impl

import org.babyfish.jimmer.Page
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.napbad.clientbackend.service.ArticleService
import org.napbad.model.dto.article.ArticleCreateInput
import org.napbad.model.dto.article.ArticleCreateOutput
import org.napbad.model.dto.article.ArticleQueryOutput
import org.napbad.model.dto.article.ArticleSpecification
import org.napbad.model.entity.Article
import org.springframework.stereotype.Service

@Service
class ArticleServiceImpl(
    val sqlClient: KSqlClient
) : ArticleService {
    override fun add(article: ArticleCreateInput): ArticleCreateOutput {
        var saveResult = sqlClient.save(article)
        return ArticleCreateOutput(saveResult.modifiedEntity)
    }

    override fun query(article: ArticleSpecification): Page<ArticleQueryOutput> {

        var list = sqlClient.createQuery(Article::class) {
            where(article)
            select(
                table.fetch (
                    ArticleQueryOutput::class
                )
            )
        }.fetchPage(
            article.pageNum!!,
            article.pageSize!!
        )

        return list
    }
}