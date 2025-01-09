package com.example.mazadytask.data.model.categories

import com.squareup.moshi.Json

data class Children(
    @Json(name = "circle_icon") val circleIcon: String= "",
    val description: String = "",
    @Json(name = "disable_shipping") val disableShipping: Int = 0,
    val id: Int = 0,
    val image: String = "",
    val name: String = "",
    val slug: String = ""
)