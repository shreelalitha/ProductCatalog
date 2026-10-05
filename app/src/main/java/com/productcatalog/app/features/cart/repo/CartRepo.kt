package com.productcatalog.app.features.cart.repo

import com.productcatalog.app.data.room.CartDao
import com.productcatalog.app.data.room.CartEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepo @Inject constructor(private val cartDao: CartDao) {

    fun getAllCartItems(): Flow<List<CartEntity>> {
        return cartDao.getAllCartItems()
    }

    suspend fun insertCartItem(item: CartEntity) {
        cartDao.insertCartItem(item)
    }

    suspend fun updateCartItem(item: CartEntity) {
        cartDao.updateCartItem(item)
    }

    suspend fun deleteCartItem(item: CartEntity) {
        cartDao.deleteCartItem(item)
    }
}