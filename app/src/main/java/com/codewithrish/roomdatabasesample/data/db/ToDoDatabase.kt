package com.codewithrish.roomdatabasesample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codewithrish.roomdatabasesample.data.db.dao.TaskDao
import com.codewithrish.roomdatabasesample.data.db.entity.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}