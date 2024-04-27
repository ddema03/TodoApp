package com.example.todoapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.theme.TodoAppTheme
import com.example.todoapp.TodoViewModel
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @SuppressLint("ComposableDestinationInComposeScope")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this  )

        setContent {
            TodoAppTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen(navController = navController)
                        }
                        composable("register") {
                            RegisterScreen(navController = navController)
                        }

                        composable("todo") {
                        // Ensure passing the correct argument
                            // Create the ViewModel for the Todo list page
                            val todoViewModel = androidx.lifecycle.viewmodel.compose.viewModel<TodoViewModel>()
                            TodoListPage(viewModel = todoViewModel)
                            composable("todo_list") { TodoListPage() }// Pass the correct argument
                        }
                    }
                }
            }
        }
    }

}
@Composable
fun LoginScreen(navController: NavController){
    var scale by remember {
        mutableStateOf(0.5f)
    }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(700)
        )
        val scaleValues = listOf(0.5f,0.7f,1.0f,1.3f,1.5f)
        scaleValues.forEach { targetScale -> scale = targetScale
            delay(500) }

        delay(600)
        navController.navigate("login")

    }
    Surface (
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()

    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = R.drawable.notes), contentDescription = "Logo",
                modifier = Modifier.scale(scale))

        }

    }
}