package com.productcatalog.app.features.product.repo

import com.productcatalog.app.data.retrofit.ProductApi
import com.productcatalog.app.features.product.model.Product
import com.productcatalog.app.features.product.model.ProductResponse
import javax.inject.Inject

class ProductRepo @Inject constructor(
    private val productApi: ProductApi
) {
    suspend fun getProducts(): ProductResponse {
        return productApi.getProducts()
    }

    suspend fun getProduct(id: Int): Product {
        return productApi.getProduct(id)
    }

    suspend fun searchProducts(query: String): ProductResponse {
        return productApi.searchProducts(query)
    }
}