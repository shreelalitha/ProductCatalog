package com.productcatalog.app.data.retrofit

import com.productcatalog.app.features.product.model.Product
import com.productcatalog.app.features.product.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products/{id}")
    suspend fun getProduct(
        @Path("id") id: Int
    ): Product

    @GET("products/search")
    suspend fun searchProducts(
        @Query("q") query: String
    ): ProductResponse
}