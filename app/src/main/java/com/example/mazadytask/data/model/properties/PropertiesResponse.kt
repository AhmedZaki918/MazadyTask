package com.example.mazadytask.data.model.properties


data class PropertiesResponse(
    val code: Int = 0,
    val data: List<PropertiesData> = listOf(),
    val msg: String = ""
)