package com.example.myapplication.data.model

import com.squareup.moshi.Json

class Search (
    @field:Json(name = "breeds")
    val breed: List<Breed>,
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "url")
    val url: String,
    @field:Json(name = "width")
    val width: String,
    @field:Json(name = "height")
    val height: String,
    )