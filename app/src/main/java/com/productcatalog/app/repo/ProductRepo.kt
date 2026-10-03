package com.productcatalog.app.repo

import com.productcatalog.app.data.retrofit.ProductApi
import com.productcatalog.app.model.ProductResponse
import javax.inject.Inject

class ProductRepo @Inject constructor(
    private val productApi: ProductApi
) {
    suspend fun getProducts(): ProductResponse {
        return productApi.getProducts()
    }
}