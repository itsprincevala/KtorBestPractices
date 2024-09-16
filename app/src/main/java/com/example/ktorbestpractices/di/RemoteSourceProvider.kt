package com.example.ktorbestpractices.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceProvider {

    @Qualifier
    annotation class BaseHttpClient

    @Qualifier
    annotation class EmployeeHttpClient

    @Qualifier
    annotation class KtorHttpClient

    @Provides
    @BaseHttpClient
    fun provideBaseClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        encodeDefaults = true
                        explicitNulls = false
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                this.level = LogLevel.ALL
                this.logger = Logger.Companion.SIMPLE
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }

    @Provides
    @KtorHttpClient
    fun provideKtorRemoteSource(
        @BaseHttpClient client: HttpClient
    ): HttpClient {
        return client.config {
            defaultRequest {
                url("https://ktor.io/docs/")
            }
        }
    }

    @Provides
    @EmployeeHttpClient
    fun provideEmployeeRemoteSource(
        @BaseHttpClient client: HttpClient
    ): HttpClient {
        return client.config {
            defaultRequest {
                url("https://dummy.restapiexample.com/api/v1/")
            }
        }
    }

}