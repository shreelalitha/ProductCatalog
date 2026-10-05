package com.productcatalog.app.features.cart.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.productcatalog.app.features.cart.model.CartItem
import com.productcatalog.app.features.cart.repo.CartRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repo: CartRepo): ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())

    val cartItems: StateFlow<List<CartItem>> = _cartItems

    fun getCartItems() {
        viewModelScope.launch {
            repo.getAllCartItems().collect { list ->
                val cartData = list.map { singleItem ->
                    CartItem(
                        productId = singleItem.productId,
                        title = singleItem.title,
                        price = singleItem.price,
                        thumbnail = singleItem.thumbnail,
                        quantity = singleItem.quantity
                    )
                }

                _cartItems.value = cartData
            }
        }
    }
}