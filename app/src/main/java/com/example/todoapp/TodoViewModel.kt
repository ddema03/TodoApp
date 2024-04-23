package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.UUID

class TodoViewModel : ViewModel() {
    private val _todoList = MutableLiveData<List<TodoItem>>(emptyList())
    val todoList: LiveData<List<TodoItem>> = _todoList

    fun addTodo(title: String) {
        if (title.isNotBlank()) {
            val newTodo = TodoItem(title = title)
            _todoList.value = _todoList.value?.plus(newTodo)
        }
    }

    fun deleteTodo(id: UUID) {
        _todoList.value = _todoList.value?.filter { it.id != id }
    }
}
