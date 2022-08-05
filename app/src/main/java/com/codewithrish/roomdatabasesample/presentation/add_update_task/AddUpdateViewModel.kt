package com.codewithrish.roomdatabasesample.presentation.add_update_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithrish.roomdatabasesample.data.db.entity.Task
import com.codewithrish.roomdatabasesample.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUpdateViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {
    // Add Task
    fun addTask(
        task: Task
    ) = viewModelScope.launch {
        taskRepository.addTask(task)
    }

    // Update Task
    fun updateTask(
        task: Task
    ) = viewModelScope.launch {
        taskRepository.updateTask(task)
    }
}