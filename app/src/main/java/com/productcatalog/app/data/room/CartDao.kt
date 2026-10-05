package com.productcatalog.app.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): Flow<List<CartEntity>>

    @Insert
    suspend fun insertCartItem(item: CartEntity)

    @Update
    suspend fun updateCartItem(item: CartEntity)

    @Delete
    suspend fun deleteCartItem(item: CartEntity)
}