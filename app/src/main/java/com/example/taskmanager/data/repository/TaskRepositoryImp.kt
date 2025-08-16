package com.example.taskmanager.data.repository

import com.example.taskmanager.data.entity.TaskEntity
import com.example.taskmanager.data.local.TaskDAO
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImp(
    private val taskDAO: TaskDAO
) : TaskRepository {
    override fun getUncompletedTasks(): Flow<List<TaskEntity>> {
        return taskDAO.getUncompletedTasks()
    }

    override fun getCompletedTasks(): Flow<List<TaskEntity>> {
        return taskDAO.getCompletedTasks()
    }

    override fun getFavoriteTasks(): Flow<List<TaskEntity>> {
        return taskDAO.getFavoriteTasks()
    }

    override suspend fun insertTask(task: TaskEntity) {
        return taskDAO.insertTask(task)
    }

    override suspend fun updateTask(task: TaskEntity) {
        return taskDAO.updateTask(task)
    }

    override suspend fun deleteTask(task: TaskEntity) {
        return taskDAO.deleteTask(task)
    }
}