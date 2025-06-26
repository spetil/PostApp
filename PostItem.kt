package com.example.postapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.postapp.data.models.Post

@Composable
fun PostItem(post: Post, onDelete: (Int) -> Unit, onEdit: (Post) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFB3E5FC))
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(post.title, style = MaterialTheme.typography.titleLarge, color = Color(0xFF0D47A1))
            Spacer(Modifier.height(4.dp))
            Text(post.content, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF01579B))
            Spacer(Modifier.height(8.dp))
            Row {
                Button(
                    onClick = { showDialog = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                    modifier = Modifier.padding(end = 8.dp)
                ) { Text("Deletar", color = Color.White) }

                Button(
                    onClick = { onEdit(post) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
                ) { Text("Editar", color = Color.White) }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Confirmar Exclusão", color = Color(0xFFD32F2F)) },
            text = { Text("Tem certeza que deseja excluir este post?") },
            confirmButton = {
                TextButton(onClick = { onDelete(post.id); showDialog = false }) {
                    Text("Sim", color = Color(0xFFD32F2F))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar", color = Color.Gray)
                }
            }
        )
    }
}
