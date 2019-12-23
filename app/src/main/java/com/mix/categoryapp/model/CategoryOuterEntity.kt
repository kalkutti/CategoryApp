package com.mix.categoryapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CategoryOuterEntity (

    @Json(name = "categories")
    var categories: MutableList<CategoryDetailEntity>? = null

)