package com.example.ktorbestpractices.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUsersResponseModel(
    @SerialName("page") val page: Int,
    @SerialName("per_page") val perPage: Int,
    @SerialName("total") val total: Int,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("data") val data: List<UserModel>,
    @SerialName("support") val support: Support
)

@Serializable
data class Support(
    @SerialName("url") val url: String,
    @SerialName("text") val text: String
)