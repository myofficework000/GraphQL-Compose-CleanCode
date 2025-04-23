package com.adityanarvekar.graphqlcountrylist.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adityanarvekar.graphqlcountrylist.presentation.theme.GraphQLCountryListTheme
import com.adityanarvekar.graphqlcountrylist.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GraphQLCountryListTheme {
                ShowCountry(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
//use case should fetch data from repository
@Composable
fun ShowCountry(viewModel: MainViewModel = viewModel(), modifier: Modifier) {
    val countries by viewModel.graphQLResponseModel.collectAsState()
    countries?.let { countryList ->
        LazyColumn(modifier = modifier) {
            items(countryList) { country->
                Text(text = country.name)
            }
        }
    }
}