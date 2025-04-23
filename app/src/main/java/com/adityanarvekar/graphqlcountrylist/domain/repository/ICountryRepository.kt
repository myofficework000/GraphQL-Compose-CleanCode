package com.adityanarvekar.graphqlcountrylist.domain.repository

import com.codegalaxy.graphqldemo.model.GetCountriesQuery
import kotlinx.coroutines.flow.Flow

interface ICountryRepository {
    fun getCountryListFromApi(): Flow<List<GetCountriesQuery.Country>>
}