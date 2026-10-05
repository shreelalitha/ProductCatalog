package com.productcatalog.app.features.product.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.productcatalog.app.data.room.CartEntity
import com.productcatalog.app.features.cart.repo.CartRepo
import com.productcatalog.app.features.product.model.Product
import com.productcatalog.app.features.product.repo.ProductRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repo: ProductRepo
) : ViewModel() {

    private val _productState = MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)
    val productState: StateFlow<ProductDetailUiState> = _productState

    fun getProduct(id: Int) {
        _productState.value = ProductDetailUiState.Loading

        viewModelScope.launch {
            try {
                val product = repo.getProduct(id)
                _productState.value = ProductDetailUiState.Success(product)
            } catch (e: Exception) {
                _productState.value = ProductDetailUiState.Error(
                        e.message.toString()
                )
            }
        }
    }
}