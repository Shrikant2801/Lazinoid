package com.example.lazinoid.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazinoid.data.Task
import com.example.lazinoid.data.TaskDao
import com.example.lazinoid.data.TaskDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val dao: TaskDao = TaskDatabase.getDatabase(application).taskDao()

    val tasks = dao.getAllTasks().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    private val sarcasmQuotes = listOf(
        "Adding a task? Who are you trying to impress?",
        "Wow, productivity looks good on you.",
        "Sure… like you’ll finish it."
    )

    fun addTask(title: String) {
        if (title.isNotBlank()) {
            viewModelScope.launch {
                dao.insertTask(Task(title = title))
            }
            Toast.makeText(getApplication(), sarcasmQuotes.random(), Toast.LENGTH_SHORT).show()
        }
    }

    fun toggleComplete(task: Task) {
        viewModelScope.launch {
            dao.updateTask(task.copy(completed = !task.completed))
        }
        Toast.makeText(getApplication(), "Finally, you moved your finger!", Toast.LENGTH_SHORT).show()
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            dao.deleteTask(task)
        }
        Toast.makeText(getApplication(), "Yeah, like you were going to do it anyway.", Toast.LENGTH_SHORT).show()
    }
}
