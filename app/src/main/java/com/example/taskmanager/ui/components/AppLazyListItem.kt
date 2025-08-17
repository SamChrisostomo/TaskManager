package com.example.taskmanager.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

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
        headlineContent = { Text(title) },
        supportingContent = { Text(subtitle) },
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
                        false -> Icons.TwoTone.Star
                        else -> Icons.Filled.Star
                    },
                    contentDescription = null
                )
            }
        }
    )
}