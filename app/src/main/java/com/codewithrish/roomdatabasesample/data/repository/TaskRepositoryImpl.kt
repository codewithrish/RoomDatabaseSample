package com.codewithrish.roomdatabasesample.data.repository

import com.codewithrish.roomdatabasesample.data.db.dao.TaskDao
import com.codewithrish.roomdatabasesample.data.db.entity.Task
import com.codewithrish.roomdatabasesample.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
): TaskRepository {
    override suspend fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    override suspend fun getAllTasks(): List<Task> {
        return taskDao.getAllTasks()
    }

}