package com.productcatalog.app.features.product.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.productcatalog.app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(showBackButton: Boolean = false, onBackClick: () -> Unit = {}) {
    TopAppBar(
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(R.drawable.back),
                        contentDescription = "Back"
                    )
                }
            }
        },
        title = {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = "Home",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "IISc, Bengaluru",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
    )
}