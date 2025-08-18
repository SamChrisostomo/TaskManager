package com.example.taskmanager.ui.homescreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeScreenRoute(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        navController = navController,
        uiState,
        onCompletedChange = viewModel::onTaskCompletitionChanged,
        onFavoriteToggle = viewModel::onTaskFavoriteToggled,
        onSelectTab = viewModel::onSelectTab
    )
}