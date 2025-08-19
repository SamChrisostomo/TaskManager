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
    val newTask by viewModel.taskStateFlow.collectAsState()

    HomeScreen(
        navController = navController,
        uiState,
        newTask = newTask,
        onCompletedChange = viewModel::onTaskCompletionChanged,
        onFavoriteToggle = viewModel::onTaskFavoriteToggled,
        onSelectTab = viewModel::onSelectTab,
        onShowBottomSheet = viewModel::onShowBottomSheet,
        onTaskTitleChanged = viewModel::onTaskTitleChanged,
        onTaskDescriptionChanged = viewModel::onTaskDescriptionChanged,
        onTaskFavoriteChanged = viewModel::onTaskFavoriteChanged,
        addNewTask = viewModel::addNewTask
    )
}