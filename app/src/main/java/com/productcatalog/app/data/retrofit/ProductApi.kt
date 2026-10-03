package com.productcatalog.app.data.retrofit

import com.productcatalog.app.features.product.model.ProductResponse
import retrofit2.http.GET

interface ProductApi {

    @GET("products")
    suspend fun getProducts(): ProductResponse
}