package com.productcatalog.app.features.product.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.productcatalog.app.features.product.viewModel.ProductUiState
import com.productcatalog.app.features.product.viewModel.ProductViewModel

@Composable
fun ProductScreen(viewModel: ProductViewModel = hiltViewModel()){
    val state by viewModel.prodStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAllProducts()
    }

    when (val currentState = state) {
        ProductUiState.Loading -> {
            Text("Loading...")
        }

        is ProductUiState.Success -> {
            Column {
                currentState.prodcuts.forEach { product ->
                    Text(product.title)
                }
            }
        }

        is ProductUiState.Error -> {
            Column {
                Text(currentState.message)
            }
        }

    }
}