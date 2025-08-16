package com.example.taskmanager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmanager.data.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDAO
}