package com.codewithrish.roomdatabasesample.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithrish.roomdatabasesample.data.db.entity.Task
import com.codewithrish.roomdatabasesample.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {
    private val _taskList: MutableLiveData<List<Task>> = MutableLiveData()
    val taskList: MutableLiveData<List<Task>>
        get() = _taskList

    init {
        getAllTasks()
    }

    // Delete Task
    fun deleteTask(
        task: Task
    ) = viewModelScope.launch {
        taskRepository.deleteTask(task)
    }

    // Get All Task
    fun getAllTasks() = viewModelScope.launch {
        _taskList.value = taskRepository.getAllTasks()
    }

}