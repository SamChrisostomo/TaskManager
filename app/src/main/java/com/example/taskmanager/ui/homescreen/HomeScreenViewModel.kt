package com.example.taskmanager.ui.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.entity.TaskEntity
import com.example.taskmanager.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor
    (
    private val taskRepository: TaskRepository
) : ViewModel() {
    val taskStateFlow = MutableStateFlow(NewTask())
    private val _completedTasks = taskRepository.getCompletedTasks()
    private val _uncompletedTasks = taskRepository.getUncompletedTasks()
    private val _favoriteTasks = taskRepository.getFavoriteTasks()
    private var _tabIndexSelected = MutableStateFlow(0)
    private var _showBottomSheet = MutableStateFlow(false)

    val uiState: StateFlow<HomeScreenUiState> = combine(
        _uncompletedTasks,
        _favoriteTasks,
        _completedTasks,
        _tabIndexSelected,
        _showBottomSheet
    ) { uncompleted, favorite, completed, tabSelected, showBottomSheet ->
        HomeScreenUiState(
            uncompletedTasks = uncompleted,
            favoriteTasks = favorite,
            completedTasks = completed,
            tabIndexSelected = tabSelected,
            showBottomSheet = showBottomSheet,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeScreenUiState()
    )

    fun onTaskCompletionChanged(task: TaskEntity, isCompleted: Boolean) {
        viewModelScope.launch {
            taskRepository.updateTask(task.copy(isCompleted = isCompleted))
        }
    }

    fun onTaskFavoriteToggled(task: TaskEntity) {
        viewModelScope.launch {
            taskRepository.updateTask(task.copy(isFavorite = !task.isFavorite))
        }
    }

    fun onSelectTab(index: Int) {
        _tabIndexSelected.value = index
    }

    fun onShowBottomSheet(show: Boolean) {
        _showBottomSheet.value = show
    }

    fun onTaskTitleChanged(title: String) {
        taskStateFlow.update { currentValue ->
            currentValue.copy(title = title)
        }
    }

    fun onTaskDescriptionChanged(description: String) {
        taskStateFlow.update { currentValue ->
            currentValue.copy(description = description)
        }
    }

    fun onTaskFavoriteChanged(isFavorite: Boolean) {
        taskStateFlow.update { currentValue ->
            currentValue.copy(isFavorite = isFavorite)
        }
    }

    fun addNewTask() {
        viewModelScope.launch {
            taskRepository.insertTask(
                TaskEntity(
                    title = taskStateFlow.value.title,
                    description = taskStateFlow.value.description,
                    isFavorite = taskStateFlow.value.isFavorite
                )
            )
        }
    }
}

data class HomeScreenUiState(
    val completedTasks: List<TaskEntity> = emptyList(),
    val uncompletedTasks: List<TaskEntity> = emptyList(),
    val favoriteTasks: List<TaskEntity> = emptyList(),
    val tabIndexSelected: Int = 0,
    val showBottomSheet: Boolean = false,
)

data class NewTask(
    val title: String = "",
    val description: String = "",
    val isFavorite: Boolean = false,
)