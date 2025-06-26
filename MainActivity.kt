package com.example.postapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.postapp.ui.screens.MainScreen
import com.example.postapp.ui.theme.PostAppTheme
import com.example.postapp.viewmodel.PostViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostAppTheme {
                val viewModel = PostViewModel()
                MainScreen(viewModel)
            }
        }
    }
}
