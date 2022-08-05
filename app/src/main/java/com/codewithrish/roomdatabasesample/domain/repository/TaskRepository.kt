package com.codewithrish.roomdatabasesample.domain.repository

import com.codewithrish.roomdatabasesample.data.db.entity.Task

interface TaskRepository {
    suspend fun addTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun getAllTasks(): List<Task>
}