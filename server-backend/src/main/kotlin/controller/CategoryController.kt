package org.napbad.serverbackend.controller

import lombok.extern.slf4j.Slf4j
import org.babyfish.jimmer.client.meta.Api
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("category-server")
class CategoryController {


    fun queryCategory() {

    }

}