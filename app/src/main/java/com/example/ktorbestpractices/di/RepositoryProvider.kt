package com.example.ktorbestpractices.di

import com.example.ktorbestpractices.data.repositories.UsersRepository
import com.example.ktorbestpractices.data.repositories.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryProvider {

    @Binds
    abstract fun bindEmployeeRepository(
        employeeRepository: UsersRepositoryImpl
    ): UsersRepository

}