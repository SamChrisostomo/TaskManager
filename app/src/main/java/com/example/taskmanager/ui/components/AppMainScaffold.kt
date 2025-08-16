package com.example.taskmanager.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun AppMainScaffold(
    title: String,
    modifier: Modifier = Modifier,
    navController: NavController,
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    val canNavigateBack = navController.previousBackStackEntry != null
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            AppTopBar(
                title = title,
                canNavigateBack = canNavigateBack,
                onBackStack = { navController.navigateUp() }
            )
        },
        modifier = modifier,
        floatingActionButton = floatingActionButton,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        content(innerPadding)
    }
}