package com.productcatalog.app.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CartEntity::class], version = 1)
abstract class CartDB: RoomDatabase() {
    abstract fun cartDao(): CartDao
}