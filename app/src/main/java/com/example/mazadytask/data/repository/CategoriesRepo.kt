package com.example.mazadytask.data.repository

import com.example.mazadytask.data.network.APIService
import com.example.mazadytask.data.network.SafeApiCall
import javax.inject.Inject

class CategoriesRepo @Inject constructor(
    private val api: APIService
) : SafeApiCall {

    suspend fun allCategories() = safeApiCall {
        api.getAllCats()
    }

    suspend fun properties(categoryId: Int) = safeApiCall {
        api.properties(categoryId = categoryId)
    }
}
