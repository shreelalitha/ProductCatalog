package com.productcatalog.app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.productcatalog.app.repo.ProductRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repo: ProductRepo
): ViewModel(){

    private val _prodStateFlow = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val prodStateFlow: StateFlow<ProductUiState> = _prodStateFlow

    fun getAllProducts(){
        _prodStateFlow.value = ProductUiState.Loading
        viewModelScope.launch {
            try{
                val response = repo.getProducts().products
                _prodStateFlow.value = ProductUiState.Success(response)
            }catch (e: Exception){
                _prodStateFlow.value = ProductUiState.Error(e.message.toString())
            }
        }
    }
}