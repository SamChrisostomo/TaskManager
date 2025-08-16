package com.example.taskmanager.data.repository

import com.example.taskmanager.data.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getUncompletedTasks(): Flow<List<TaskEntity>>
    fun getCompletedTasks(): Flow<List<TaskEntity>>
    fun getFavoriteTasks(): Flow<List<TaskEntity>>
    suspend fun insertTask(task: TaskEntity)
    suspend fun updateTask(task: TaskEntity)
    suspend fun deleteTask(task: TaskEntity)
}