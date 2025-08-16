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
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(taskRepository: TaskRepository) : ViewModel() {
    val completedTask: StateFlow<List<TaskEntity>> = taskRepository.getCompletedTasks()
        .map { tasks -> tasks.map { TaskEntity(it.id, it.title, it.description, it.isFavorite) } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList<TaskEntity>()
        )
}