package com.example.mazadytask.data.model.properties


import com.squareup.moshi.Json

data class PropertiesData(
    val description: String = "",
    val id: Int = 0,
    val list: Boolean = false,
    val name: String = "",
    val options: List<Option> = listOf(),
    @Json(name = "other_value") val otherValue: Any = Any(),
    val parent: Any = Any(),
    val slug: String = "",
    val type: String = "",
    val value: String = ""
)