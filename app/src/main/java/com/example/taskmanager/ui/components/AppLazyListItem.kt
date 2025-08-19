package com.example.taskmanager.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun AppLazyListItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    isCompleted: Boolean,
    isFavorite: Boolean,
    onCompletedChange: (Boolean) -> Unit,
    onFavoriteToggle: () -> Unit,
    onItemClick: () -> Unit
) {
    ListItem(
        modifier = modifier.clickable(
            onClick = onItemClick
        ),
        headlineContent = {
            Text(
                title,
                style = MaterialTheme.typography.headlineSmall,
                textDecoration = when (isCompleted) {
                    false -> TextDecoration.None
                    else -> TextDecoration.LineThrough
                }
            )
        },
        supportingContent = {
            Text(
                subtitle,
                style = MaterialTheme.typography.bodyMedium,
                textDecoration = when (isCompleted) {
                    false -> TextDecoration.None
                    else -> TextDecoration.LineThrough
                }
            )
        },
        leadingContent = {
            Checkbox(
                checked = isCompleted,
                onCheckedChange = onCompletedChange
            )
        },
        trailingContent = {
            IconButton(onClick = onFavoriteToggle) {
                Icon(
                    imageVector = when (isFavorite) {
                        false -> Icons.Filled.FavoriteBorder
                        else -> Icons.Filled.Favorite
                    },
                    contentDescription = null
                )
            }
        }
    )
}