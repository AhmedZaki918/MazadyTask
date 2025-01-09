package com.example.mazadytask.data.model.properties


import com.google.gson.annotations.SerializedName

data class Option(
    val child: Boolean = false,
    val id: Int = 0,
    val name: String = "",
    val parent: Int = 0,
    val slug: String = ""
)