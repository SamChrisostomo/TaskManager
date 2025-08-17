package com.example.taskmanager.ui.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager.data.entity.TaskEntity
import com.example.taskmanager.ui.components.AllTaskCompleted
import com.example.taskmanager.ui.components.AppLazyListItem
import com.example.taskmanager.ui.components.AppMainScaffold

@Composable
fun HomeScreen(
    navController: NavController,
    uiState: HomeScreenUiState,
    onCompletedChange: (TaskEntity, Boolean) -> Unit,
    onFavoriteToggle: (TaskEntity) -> Unit
) {
    val taskListState: LazyListState = rememberLazyListState()

    AppMainScaffold(
        title = "Tarefas",
        navController = navController,
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                state = taskListState,
            ) {
                if (uiState.tasks.isEmpty()) {
                    item {
                        AllTaskCompleted(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                } else {
                    items(
                        items = uiState.tasks,
                        key = { task -> task.id }
                    ) { task ->
                        HorizontalDivider()
                        AppLazyListItem(
                            title = task.title,
                            subtitle = task.description,
                            isCompleted = task.isCompleted,
                            isFavorite = task.isFavorite,
                            onCompletedChange = { isCompleted ->
                                onCompletedChange(task, isCompleted)
                            },
                            onFavoriteToggle = {
                                onFavoriteToggle(task)
                            },
                            onItemClick = {}
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = NavController(LocalContext.current),
        uiState = HomeScreenUiState(),
        onCompletedChange = { _, _ -> },
        onFavoriteToggle = {}
    )
}