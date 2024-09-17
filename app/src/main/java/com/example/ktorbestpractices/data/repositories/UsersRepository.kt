package com.example.ktorbestpractices.data.repositories

import com.example.ktorbestpractices.data.models.UserModel

interface UsersRepository {
    suspend fun getUsers(): GetUsersResponse
}

sealed interface GetUsersResponse {

    data class Success(
        val users: List<UserModel>
    ): GetUsersResponse

    data class Error(val message: String): GetUsersResponse

}