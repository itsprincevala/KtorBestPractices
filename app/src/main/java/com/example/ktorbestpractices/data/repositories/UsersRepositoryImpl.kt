package com.example.ktorbestpractices.data.repositories

import com.example.ktorbestpractices.data.models.GetUsersResponseModel
import com.example.ktorbestpractices.di.RemoteSourceProvider
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    @RemoteSourceProvider.UserHttpClient private val httpClient: HttpClient
): UsersRepository {
    override suspend fun getUsers(): GetUsersResponse {
        val response = httpClient.get("users")
        return if(response.status.isSuccess()) {
            val responseModel: GetUsersResponseModel = response.body()
            GetUsersResponse.Success(responseModel.data)
        } else {
            GetUsersResponse.Error("Something went wrong")
        }
    }
}