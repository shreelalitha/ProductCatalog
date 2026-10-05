package com.productcatalog.app.features.cart.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.productcatalog.app.R
import com.productcatalog.app.features.cart.model.CartItem

@Composable
fun CartItemRow(
    item: CartItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onDelete: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = item.thumbnail,
            contentDescription = item.title,
            modifier = Modifier.size(60.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = item.title,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                IconButton(onClick = onDelete) {
                    Icon(
                        painter = painterResource(R.drawable.delete),
                        contentDescription = "Delete"
                    )
                }
            }

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {

                Text(text = "₹${item.price}")

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = onDecrease) {
                    Icon(
                        painter = painterResource(R.drawable.remove),
                        contentDescription = "Decrease"
                    )
                }

                Text(text = item.quantity.toString())

                IconButton(onClick = onIncrease) {
                    Icon(
                        painter = painterResource(R.drawable.add_plain),
                        contentDescription = "Increase"
                    )
                }
            }
        }
    }
}