package com.productcatalog.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.productcatalog.app.features.cart.screens.CartScreen
import com.productcatalog.app.features.product.screens.productDetail.ProductDetailScreen
import com.productcatalog.app.features.product.screens.productsDisplay.ProductScreen
import com.productcatalog.app.ui.theme.ProductCatalogTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductCatalogTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {

                    composable("home") {
                        ProductScreen(
                            onProductClick = { productId->
                                navController.navigate("product/$productId")
                            },
                            onCartClick = {
                                navController.navigate("cart")
                            }
                        )
                    }

                    composable("cart") {
                        CartScreen(
                            onBackClick = {
                                navController.popBackStack()
                            },
                            onHomeClick = {
                                navController.navigate("home")
                            }
                        )
                    }

                    composable("product/{productId}") { backStackEntry ->

                        val productId = backStackEntry.arguments
                            ?.getString("productId")
                            ?.toIntOrNull()

                        if (productId != null) {
                            ProductDetailScreen(
                                productId = productId,
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}