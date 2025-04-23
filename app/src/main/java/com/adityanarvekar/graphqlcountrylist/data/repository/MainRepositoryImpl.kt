package com.adityanarvekar.graphqlcountrylist.data.repository

import com.adityanarvekar.graphqlcountrylist.domain.repository.ICountryRepository
import com.apollographql.apollo.ApolloClient
import com.codegalaxy.graphqldemo.model.GetCountriesQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : ICountryRepository {
    override fun getCountryListFromApi(): Flow<List<GetCountriesQuery.Country>> = flow {
        val countries = apolloClient.query(GetCountriesQuery()).execute().data?.countries
        countries?.let { emit(it) }
    }.flowOn(Dispatchers.IO)
}