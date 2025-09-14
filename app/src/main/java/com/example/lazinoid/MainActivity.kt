package com.example.lazinoid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.lazinoid.screen.TaskScreen
import com.example.lazinoid.ui.theme.LazinoidTheme
import com.example.lazinoid.viewmodel.TaskViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by rememberSaveable { mutableStateOf(false) }
            LazinoidTheme(darkTheme = darkTheme) {
                val viewModel: TaskViewModel = viewModel(factory = viewModelFactory {
                    initializer {
                        TaskViewModel(application)
                    }
                })

                TaskScreen(
                    viewModel = viewModel,
                    darkTheme = darkTheme,
                    onToggleTheme = { darkTheme = !darkTheme }
                )
            }
        }
    }
}
