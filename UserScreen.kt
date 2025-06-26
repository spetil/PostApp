package com.example.postapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.postapp.viewmodel.PostViewModel

@Composable
fun UserScreen(viewModel: PostViewModel) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                viewModel.createUser(
                    name = name,
                    email = email,
                    onSuccess = {
                        name = ""
                        email = ""
                    },
                    onError = {
                        // Tratar erro
                    }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Criar Usuário")
        }
    }
}
