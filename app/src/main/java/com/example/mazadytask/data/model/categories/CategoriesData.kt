package com.example.mazadytask.data.model.categories

import com.squareup.moshi.Json

data class CategoriesData(
    @Json(name = "ads_banners") val adsBanners: List<AdsBanner> = listOf(),
    @Json(name = "categories") val categories: List<Category> = listOf(),
    @Json(name = "google_version") val googleVersion: String = "",
    @Json(name = "huawei_version") val huaweiVersion: String = "",
    @Json(name = "ios_latest_version") val iosLatestVersion: String ="",
    @Json(name = "ios_version") val iosVersion: String = "",
    val statistics: Statistics = Statistics()
)