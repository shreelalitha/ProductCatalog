package com.productcatalog.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.productcatalog.app.features.product.screens.ProductScreen
import com.productcatalog.app.ui.theme.ProductCatalogTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductCatalogTheme {
                ProductScreen(
                    onProductClick = {
                        Toast.makeText(this, "In Home", Toast.LENGTH_SHORT).show()
                    },
                    onCartClick = {
                        Toast.makeText(this, "In Cart", Toast.LENGTH_SHORT).show()
                    })
            }
        }
    }
}