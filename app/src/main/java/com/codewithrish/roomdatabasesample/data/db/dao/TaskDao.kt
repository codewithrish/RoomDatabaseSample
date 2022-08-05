package com.codewithrish.roomdatabasesample.data.db.dao

import androidx.room.*
import com.codewithrish.roomdatabasesample.data.db.entity.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM Task")
    suspend fun getAllTasks(): List<Task>

    @Insert
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}