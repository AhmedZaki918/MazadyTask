package com.example.mazadytask.data.network

import com.example.mazadytask.data.local.Constant.API_KEY
import com.example.mazadytask.data.model.categories.CategoriesResponse
import com.example.mazadytask.data.model.properties.PropertiesResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface APIService {

    @GET("get_all_cats")
    suspend fun getAllCats(
        @Header("private-key") apiKey: String = API_KEY
    ): CategoriesResponse

    @GET("properties")
    suspend fun properties(
        @Header("private-key") apiKey: String = API_KEY,
        @Query("cat") categoryId : Int
    ): PropertiesResponse
}