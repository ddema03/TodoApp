package com.example.todoapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "login_screen") {
                composable("login_screen") {
                    LoginScreen(navController)
                }

                composable("register_screen") {
                    RegisterScreen(navController = navController)
                }
                composable("todo_list_page") {
                    TodoListPage(todoViewModel)
                }

//            TodoAppTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    TodoListPage(todoViewModel)
//                }
                }
            }
        }
    }