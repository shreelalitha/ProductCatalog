package com.productcatalog.app.features.product.repo

import com.productcatalog.app.data.retrofit.ProductApi
import com.productcatalog.app.features.product.model.Product
import com.productcatalog.app.features.product.model.ProductResponse
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class ProductRepo @Inject constructor(
    private val productApi: ProductApi
) {
    suspend fun getProducts(): ProductResponse {
        return try {
            productApi.getProducts()
        } catch (e: SocketTimeoutException) {
            throw Exception("Oops! The request took too long")
        } catch (e: IOException) {
            throw Exception("Oops! There is no internet")
        } catch (e: Exception) {
            throw Exception("Uh oh! Unable to load products")
        }
    }

    suspend fun getProduct(id: Int): Product {
        return try {
            productApi.getProduct(id)
        } catch (e: SocketTimeoutException) {
            throw Exception("Oops! The request took too long")
        } catch (e: IOException) {
            throw Exception("Oops! There is no internet")
        } catch (e: Exception) {
            throw Exception("Uh oh! Unable to load this product")
        }
    }

    suspend fun searchProducts(query: String): ProductResponse {
        return try {
            productApi.searchProducts(query)
        } catch (e: SocketTimeoutException) {
            throw Exception("Oops! The request took too long")
        } catch (e: IOException) {
            throw Exception("Oops! There is no internet")
        } catch (e: Exception) {
            throw Exception("Uh oh! Unable to load products")
        }
    }
}