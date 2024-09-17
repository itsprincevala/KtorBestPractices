package com.example.ktorbestpractices.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.ktorbestpractices.ui.theme.KtorBestPracticesTheme
import com.example.ktorbestpractices.ui.viewmodels.UserDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider.create(this)[UserDetailsViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            KtorBestPracticesTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    content = { padding ->
                        UserListScreen(viewModel)
                    }
                )
            }
        }
    }
}