package com.example.taskmanager.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskmanager.data.entity.TaskEntity

@Composable
fun TaskList(
    tasks: List<TaskEntity>,
    onCompletedChange: (TaskEntity, Boolean) -> Unit,
    onFavoriteToggle: (TaskEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    val taskListState: LazyListState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        state = taskListState,
    ) {
        if (tasks.isEmpty()) {
            item {
                AllTaskCompleted(
                    modifier = Modifier.fillMaxSize()
                )
            }
        } else {
            items(
                items = tasks,
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