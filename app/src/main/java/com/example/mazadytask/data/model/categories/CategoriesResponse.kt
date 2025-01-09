package com.example.mazadytask.data.model.categories

data class CategoriesResponse(
    val code: Int = 0,
    val data: CategoriesData = CategoriesData(),
    val msg: String = ""
)