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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor
    (
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _completedTasks = taskRepository.getCompletedTasks()
    private val _uncompletedTasks = taskRepository.getUncompletedTasks()
    private val _favoriteTasks = taskRepository.getFavoriteTasks()
    private var _tabIndexSelected = MutableStateFlow<Int>(0)

    val uiState: StateFlow<HomeScreenUiState> = combine(
        _uncompletedTasks,
        _favoriteTasks,
        _completedTasks,
        _tabIndexSelected
    ) { uncompleted, completed, favorite, tabSelected ->
        HomeScreenUiState(
            completedTasks = completed,
            uncompletedTasks = uncompleted,
            favoriteTasks = favorite,
            tabIndexSelected = tabSelected
        )
    }.stateIn(
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

    fun onSelectTab(index: Int){
        _tabIndexSelected.value = index
    }
}

data class HomeScreenUiState(
    val completedTasks: List<TaskEntity> = emptyList(),
    val uncompletedTasks: List<TaskEntity> = emptyList(),
    val favoriteTasks: List<TaskEntity> = emptyList(),
    val tabIndexSelected: Int = 0
//    val isLoading: Boolean = false
)