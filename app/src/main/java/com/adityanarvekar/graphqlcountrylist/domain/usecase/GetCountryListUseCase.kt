package com.adityanarvekar.graphqlcountrylist.domain.usecase

import com.adityanarvekar.graphqlcountrylist.domain.repository.ICountryRepository

class GetCountryListUseCase(
    private val countryRepo: ICountryRepository
) {
    operator fun invoke() = countryRepo.getCountryListFromApi()
}