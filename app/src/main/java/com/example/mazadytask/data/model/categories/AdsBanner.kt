package com.example.mazadytask.data.model.categories

import com.squareup.moshi.Json

data class AdsBanner(
    val duration: Int = 0,
    val img: String = "",
    @Json(name = "media_type") val mediaType: String = ""
)