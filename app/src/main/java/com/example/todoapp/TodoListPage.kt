package com.example.todoapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth

// The main Composable function for the Todo List page
@Composable
fun TodoListPage(viewModel: TodoViewModel = viewModel()) {
    // State variable to hold the input text for new todo
    var inputText by remember { mutableStateOf("") }

    // Observing the todoList LiveData to get the current state
    val todoListState = viewModel.todoList.observeAsState(listOf())


    // Main layout for the Todo list
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("New Todo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.addTodo(inputText)
                inputText = ""
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn to display the list of Todo items
        LazyColumn {
            val todoList = todoListState.value ?: listOf() // Get the list or fallback to an empty list

            items(todoList) { todoItem ->
                TodoItemRow(
                    item = todoItem,
                    onDelete = { viewModel.deleteTodo(todoItem.id) }
                )
            }
        }
    }
}

// Composable function for displaying a single Todo item
@Composable
fun TodoItemRow(item: TodoItem, onDelete: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = item.title,
                fontSize = 20.sp
            )
            Text(
                text = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(item.createdAt),
                fontSize = 14.sp
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }
    }
}
