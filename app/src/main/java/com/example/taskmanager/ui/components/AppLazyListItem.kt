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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppLazyListItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    isCompleted: Boolean,
    isFavorite: Boolean
) {
    var isCheckboxChecked by remember { mutableStateOf(isCompleted) }
    var isFavoriteChecked by remember { mutableStateOf(isFavorite) }

    ListItem(
        modifier = modifier.clickable(
            enabled = true,
            onClickLabel = title,
            onClick = {

            }),
        headlineContent = { Text(title) },
        supportingContent = { Text(subtitle) },
        leadingContent = {
            Checkbox(
                checked = isCheckboxChecked,
                onCheckedChange = { isChecked ->
                    isCheckboxChecked = isChecked
                }
            )
        },
        trailingContent = {
            IconButton(onClick = {
                isFavoriteChecked = !isFavoriteChecked
            }) {
                Icon(
                    imageVector = when (isFavoriteChecked) {
                        false -> Icons.TwoTone.Star
                        else -> Icons.Filled.Star
                    },
                    contentDescription = null
                )
            }
        }
    )
}

@Preview
@Composable
fun AppLazyListItemPreview() {
    AppLazyListItem(
        title = "Task 1",
        subtitle = "Description of task 1",
        isCompleted = false,
        isFavorite = false
    )
}