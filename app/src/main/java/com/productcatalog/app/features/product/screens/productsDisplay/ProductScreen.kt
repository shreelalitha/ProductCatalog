package com.productcatalog.app.features.product.screens.productsDisplay

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
        if (query.isBlank()) {
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

            when (val currentState = state) {
                ProductUiState.Loading -> {
                    Text("Loading...")
                }

                is ProductUiState.Success -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
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

                is ProductUiState.Error -> {
                    Column {
                        Text(currentState.message)
                    }
                }
            }
        }
    }
}
