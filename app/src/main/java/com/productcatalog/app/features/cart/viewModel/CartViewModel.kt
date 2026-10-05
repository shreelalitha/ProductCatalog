package com.productcatalog.app.features.cart.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.productcatalog.app.data.room.CartEntity
import com.productcatalog.app.features.cart.model.CartItem
import com.productcatalog.app.features.cart.repo.CartRepo
import com.productcatalog.app.features.product.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repo: CartRepo): ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartEntity>>(emptyList())

    val cartItems: StateFlow<List<CartEntity>> = _cartItems

    fun getCartItems() {
        viewModelScope.launch {
            repo.getAllCartItems().collect { list ->
                _cartItems.value = list
            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {

            val existingItem = repo.getSpecificCartItem(product.id)

            if (existingItem == null) {
                val cartItem = CartEntity(
                    productId = product.id,
                    title = product.title,
                    price = product.price,
                    thumbnail = product.thumbnail,
                    quantity = 1
                )
                repo.insertCartItem(cartItem)

            } else {
                val updatedItem = existingItem.copy(
                    quantity = existingItem.quantity + 1
                )
                repo.updateCartItem(updatedItem)
            }
        }
    }

    fun increaseQuantity(productId: Int) {
        viewModelScope.launch {
            val existingItem = repo.getSpecificCartItem(productId)

            if (existingItem != null) {
                repo.updateCartItem(
                    existingItem.copy(
                        quantity = existingItem.quantity + 1
                    )
                )
            }
        }
    }

    fun decreaseQuantity(productId: Int) {
        viewModelScope.launch {
            val existingItem = repo.getSpecificCartItem(productId)

            if (existingItem != null) {
                if(existingItem.quantity>1) {
                    repo.updateCartItem(
                        existingItem.copy(
                            quantity = existingItem.quantity - 1
                        )
                    )
                }else{
                    repo.deleteCartItem(existingItem)
                }
            }
        }
    }

    fun deleteItemFromCart(productId: Int) {
        viewModelScope.launch {
            val existingItem = repo.getSpecificCartItem(productId)

            if (existingItem != null) {
                repo.deleteCartItem(existingItem)
            }
        }
    }
}