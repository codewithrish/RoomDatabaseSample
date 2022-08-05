package com.codewithrish.roomdatabasesample.di

import android.content.Context
import androidx.room.Room
import com.codewithrish.roomdatabasesample.common.Constants.DB_NAME
import com.codewithrish.roomdatabasesample.data.db.ToDoDatabase
import com.codewithrish.roomdatabasesample.data.db.dao.TaskDao
import com.codewithrish.roomdatabasesample.data.repository.TaskRepositoryImpl
import com.codewithrish.roomdatabasesample.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providesRoomDatabaseInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ToDoDatabase::class.java,
        DB_NAME
    ).build()

    @Provides
    @Singleton
    fun providesTaskDao(toDoDatabase: ToDoDatabase): TaskDao {
        return toDoDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun providesTaskRepository(taskDao: TaskDao): TaskRepository {
        return TaskRepositoryImpl(taskDao)
    }

}