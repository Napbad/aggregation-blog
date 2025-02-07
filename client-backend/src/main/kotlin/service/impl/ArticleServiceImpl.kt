package org.napbad.clientbackend.service.impl

import org.babyfish.jimmer.Page
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.sql
import org.napbad.clientbackend.service.ArticleService
import org.napbad.model.dto.article.*
import org.napbad.model.entity.Article
import org.springframework.stereotype.Service

@Service
class ArticleServiceImpl(
    val sqlClient: KSqlClient
) : ArticleService {
    override fun add(article: ArticleCreateInput): ArticleCreateOutput {
        val saveResult = sqlClient.insert(article)
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

    override fun delete(article: ArticleDeleteInput): String {

        sqlClient.deleteById(Article::class, article.articleId)
        return "success"
    }

    override fun update(article: ArticleUpdateInput): ArticleUpdateOutput {
        var modifiedEntity = sqlClient.update(article).modifiedEntity
        return ArticleUpdateOutput(modifiedEntity.toEntity())
    }
}