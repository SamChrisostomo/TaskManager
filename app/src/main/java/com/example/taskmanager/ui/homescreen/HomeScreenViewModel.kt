package com.example.taskmanager.ui.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.entity.TaskEntity
import com.example.taskmanager.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val taskRepository: TaskRepository) :
    ViewModel() {
    val uiState: StateFlow<HomeScreenUiState> = taskRepository.getCompletedTasks()
        .map { tasks -> HomeScreenUiState(tasks = tasks) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeScreenUiState()
        )

    fun onTaskCompletitionChanged(task: TaskEntity, isCompleted: Boolean) {
        viewModelScope.launch {
            taskRepository.updateTask(task.copy(isCompleted = isCompleted))
        }
    }

    fun onTaskFavoriteToggled(task: TaskEntity) {
        viewModelScope.launch {
            taskRepository.updateTask(task.copy(isFavorite = !task.isFavorite))
        }
    }
}

data class HomeScreenUiState(
    val tasks: List<TaskEntity> = emptyList(),
//    val isLoading: Boolean = false
)