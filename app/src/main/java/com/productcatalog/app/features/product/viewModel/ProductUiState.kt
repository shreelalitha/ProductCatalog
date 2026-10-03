package com.productcatalog.app.features.product.viewModel

import com.productcatalog.app.features.product.model.Product

interface ProductUiState {
    data object Loading : ProductUiState

    data class Success(
        val prodcuts: List<Product>
    ) : ProductUiState

    data class Error(
        val message: String
    ) : ProductUiState
}