package com.adityanarvekar.graphqlcountrylist.di

import com.adityanarvekar.graphqlcountrylist.data.repository.MainRepositoryImpl
import com.adityanarvekar.graphqlcountrylist.domain.usecase.GetCountryListUseCase
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

// Dependency injection module for providing Apollo Client and OkHttpClient
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        // Create and configure Apollo Client
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql") // GraphQL endpoint
            .okHttpClient(okHttpClient) // Configure with OkHttpClient
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        // Create and configure OkHttpClient with logging
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // Add logging interceptor
            .connectTimeout(30, TimeUnit.SECONDS) // Set connection timeout
            .readTimeout(30, TimeUnit.SECONDS) // Set read timeout
            .build()
    }

    @Singleton
    @Provides
    fun provideCountryListUseCase(repository: MainRepositoryImpl): GetCountryListUseCase {
        return GetCountryListUseCase(repository)
    }
}