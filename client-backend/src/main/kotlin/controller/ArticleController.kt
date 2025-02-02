package org.napbad.clientbackend.controller

import lombok.extern.slf4j.Slf4j
import org.babyfish.jimmer.Page
import org.babyfish.jimmer.client.meta.Api
import org.napbad.clientbackend.service.ArticleService
import org.napbad.model.dto.article.*
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("article-account")
class ArticleController (
    val articleService: ArticleService
) {


    @Api
    @PostMapping("/add")
    fun add(
        @RequestBody article: ArticleCreateInput
    ): ArticleCreateOutput
    {
        return articleService.add(article)
    }

    @Api
    @PostMapping("/query")
    fun getArticle(
        @RequestBody article: ArticleSpecification
    ): Page<ArticleQueryOutput>
    {
        return articleService.query(article)
    }

    @Api
    @PostMapping("/update")
    fun update(
        @RequestBody article: ArticleUpdateInput
    ): ArticleUpdateOutput
    {
        return articleService.update(article)
    }

    @Api
    @PostMapping("/delete")
    fun delete(
        @RequestBody article: ArticleDeleteInput
    ): String
    {
        return articleService.delete(article)
    }

}