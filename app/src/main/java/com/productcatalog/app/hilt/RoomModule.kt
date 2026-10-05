package com.productcatalog.app.hilt

import android.content.Context
import androidx.room.Room
import com.productcatalog.app.data.room.CartDB
import com.productcatalog.app.data.room.CartDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): CartDB {
        return Room.databaseBuilder(
            context,
            CartDB::class.java,
            "cart_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCartDao(database: CartDB): CartDao {
        return database.cartDao()
    }
}