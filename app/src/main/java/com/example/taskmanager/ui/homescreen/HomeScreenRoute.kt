package com.example.taskmanager.ui.homescreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.runtime.getValue

@Composable
fun HomeScreenRoute(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val completedTasks by viewModel.completedTask.collectAsState()

    HomeScreen(
        navController = navController,
        completedTasks
    )
}