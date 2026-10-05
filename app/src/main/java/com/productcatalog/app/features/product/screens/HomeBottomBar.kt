package com.productcatalog.app.features.product.screens

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.productcatalog.app.R

@Composable
fun HomeBottomBar(onHomeClick: () -> Unit, onCartClick: () -> Unit) {
    NavigationBar {
        NavigationBarItem(
            selected = true,

            onClick = onHomeClick,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.home),
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = false,
            onClick = onCartClick,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.cart),
                    contentDescription = "Cart"
                )
            },
            label = { Text("Cart") }
        )
    }
}