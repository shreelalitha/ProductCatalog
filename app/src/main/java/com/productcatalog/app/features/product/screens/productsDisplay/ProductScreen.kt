package com.productcatalog.app.features.product.screens.productsDisplay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.productcatalog.app.features.product.screens.HomeBottomBar
import com.productcatalog.app.features.product.screens.HomeTopBar
import com.productcatalog.app.features.product.viewModel.ProductUiState
import com.productcatalog.app.features.product.viewModel.ProductViewModel

@Composable
fun ProductScreen(viewModel: ProductViewModel = hiltViewModel(),
    onProductClick: (Int) -> Unit,
    onCartClick: () -> Unit
) {
    val state by viewModel.prodStateFlow.collectAsStateWithLifecycle()
    var query by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        if (query.isBlank() || query.isEmpty()) {
            viewModel.getAllProducts()
        } else {
            viewModel.searchProducts(query)
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = { HomeTopBar() },
        bottomBar = {
            HomeBottomBar(
                selectedItem = "home",
                onHomeClick = {},
                onCartClick = onCartClick
            )
        }
    ) { paddingValues ->

        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            SearchBar(
                query = query,
                onQueryChange = { query = it },
                onSearch = {
                    if (query.isBlank()) {
                        viewModel.getAllProducts()
                    } else {
                        viewModel.searchProducts(query)
                    }
                },
                onClear = {
                    query = ""
                    viewModel.getAllProducts()
                }
            )

            Spacer(modifier = Modifier.height(4.dp))

            when (val currentState = state) {
                ProductUiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is ProductUiState.Success -> {
                    if (currentState.prodcuts.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (query.isNotBlank()) {
                                    "No products found"
                                } else {
                                    "No products available"
                                }
                            )
                        }
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(currentState.prodcuts) { product ->
                                ProductCard(
                                    product = product,
                                    onProductClick = {
                                        onProductClick(product.id)
                                    },
                                    onAddClick = {

                                    }
                                )
                            }
                        }
                    }
                }

                is ProductUiState.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                currentState.message,
                                textAlign = TextAlign.Center
                            )

                            Button(
                                onClick = {
                                    if (query.isBlank()) {
                                        viewModel.getAllProducts()
                                    } else {
                                        viewModel.searchProducts(query)
                                    }
                                }
                            ) {
                                Text("Retry")
                            }
                        }
                    }
                }
            }
        }
    }
}
