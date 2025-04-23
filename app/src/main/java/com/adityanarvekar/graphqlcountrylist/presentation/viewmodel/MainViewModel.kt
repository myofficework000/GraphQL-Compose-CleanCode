package com.adityanarvekar.graphqlcountrylist.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adityanarvekar.graphqlcountrylist.domain.usecase.GetCountryListUseCase
import com.codegalaxy.graphqldemo.model.GetCountriesQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for managing UI-related data and fetching countries
@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: GetCountryListUseCase) : ViewModel() {
    // LiveData to observe GraphQL response
    private val _graphQLResponseModel = MutableStateFlow<List<GetCountriesQuery.Country>?>(
        null
    )
    val graphQLResponseModel: StateFlow<List<GetCountriesQuery.Country>?> =
        _graphQLResponseModel.asStateFlow()

    init {
        fetchCountries() // Fetch countries on initialization
    }

    // Function to fetch countries from the repository
    private fun fetchCountries() = viewModelScope.launch {
        try {
            // Get countries and post value to LiveData
            useCase().collect { result ->
                _graphQLResponseModel.value = result
            }
        } catch (exception: Exception) {
            // Handle exceptions and log error
            Log.e("ERROR", exception.message.toString())
        }
    }
}