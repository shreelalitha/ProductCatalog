package com.productcatalog.app.features.cart.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.productcatalog.app.R
import com.productcatalog.app.data.room.CartEntity
import com.productcatalog.app.features.cart.model.CartItem

@Composable
fun CartItemRow(
    item: CartEntity,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onDelete: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = item.thumbnail,
                contentDescription = item.title,
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = item.title,
                        modifier = Modifier.weight(1f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleSmall
                    )

                    IconButton(onClick = onDelete, modifier = Modifier.size(32.dp)) {
                        Icon(
                            painter = painterResource(R.drawable.delete),
                            contentDescription = "Delete",
                            tint = Color.Gray
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(onClick = onDecrease,
                        modifier = Modifier.size(32.dp)) {
                        Icon(
                            painter = painterResource(R.drawable.remove),
                            contentDescription = "Decrease",
                            tint = Color.Gray
                        )
                    }

                    Text(text = item.quantity.toString(),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(horizontal = 4.dp))

                    IconButton(
                        onClick = onIncrease,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.add_plain),
                            contentDescription = "Increase",
                            tint = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "₹${String.format("%.2f", item.price * item.quantity)}",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}