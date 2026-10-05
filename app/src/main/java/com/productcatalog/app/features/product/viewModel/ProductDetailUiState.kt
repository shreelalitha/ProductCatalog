package com.productcatalog.app.features.product.viewModel

import com.productcatalog.app.features.product.model.Product

sealed interface ProductDetailUiState {
    data object Loading : ProductDetailUiState

    data class Success(
        val product: Product
    ) : ProductDetailUiState

    data class Error(
        val message: String
    ) : ProductDetailUiState
}