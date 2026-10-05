package com.productcatalog.app.features.cart.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(
                    horizontal = 10.dp,
                    vertical = 8.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(
                    items = cartItems,
                    key = { it.productId }
                ) { item ->

                    CartItemRow(
                        item = item,
                        onIncrease = { viewModel.increaseQuantity(item.productId) },
                        onDecrease = { viewModel.decreaseQuantity(item.productId) },
                        onDelete = { viewModel.deleteItemFromCart(item.productId) }
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 12.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total Items: $totalItems",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Total Cost: ₹${String.format("%.2f",totalCost)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )
        }
    }
}