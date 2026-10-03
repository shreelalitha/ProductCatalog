package com.productcatalog.app.hilt

import com.productcatalog.app.data.retrofit.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductApiModule {

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApi{
        return retrofit.create(ProductApi::class.java)
    }
}