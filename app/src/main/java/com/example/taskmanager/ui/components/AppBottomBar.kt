package com.example.taskmanager.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        IconButton(onClick = {}, enabled = true, modifier = Modifier) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun AppBottomBarPreview() {
    AppBottomBar()
}