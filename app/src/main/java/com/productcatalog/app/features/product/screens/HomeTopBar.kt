package com.productcatalog.app.features.product.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            } else {
                IconButton(
                    onClick = {
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.hamburger),
                        contentDescription = "Menu"
                    )
                }
            }
        },
        title = {
            Column(modifier = Modifier
                .fillMaxWidth()) {
                Text(
                    text = "Hello there!",
                    style = MaterialTheme.typography.titleMedium
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.location),
                        contentDescription = "Location",
                        tint = Color.Red,
                        modifier = Modifier.size(18.dp).padding(end = 4.dp)
                    )
                    Text(
                        text = "Deliver to",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(end = 4.dp)
                    )

                    Text(
                        text = "IISc, Bengaluru",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    )
}