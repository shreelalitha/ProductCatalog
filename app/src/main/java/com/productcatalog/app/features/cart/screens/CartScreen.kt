package com.productcatalog.app.features.cart.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.productcatalog.app.features.cart.viewModel.CartViewModel
import com.productcatalog.app.features.product.screens.HomeBottomBar
import com.productcatalog.app.features.product.screens.HomeTopBar

@Composable
fun CartScreen(viewModel: CartViewModel = hiltViewModel(),
               onBackClick: () -> Unit,
               onHomeClick: () -> Unit
){

    val cartItems by viewModel.cartItems.collectAsStateWithLifecycle()
    val totalItems = cartItems.sumOf { it.quantity }
    val totalCost = cartItems.sumOf {
        it.price * it.quantity
    }

    LaunchedEffect(Unit) {
        viewModel.getCartItems()
    }

    Scaffold(
        topBar = {
            HomeTopBar(
                showBackButton = true,
                onBackClick = onBackClick
            )
        },

        bottomBar = {
            HomeBottomBar(
                selectedItem = "cart",
                onHomeClick = onHomeClick,
                onCartClick = {}
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            item {
                Text(
                    text = "Cart",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "$totalItems items"
                    )

                    Text(
                        text = "Total cost: ₹$totalCost"
                    )
                }
            }

            items(
                items = cartItems,
                key = { it.productId }
            ) { item ->

                CartItemRow(
                    item = item,
                    onIncrease = {viewModel.increaseQuantity(item.productId)},
                    onDecrease = {viewModel.decreaseQuantity(item.productId)},
                    onDelete = {viewModel.deleteItemFromCart(item.productId)}
                )
            }
        }
    }
}