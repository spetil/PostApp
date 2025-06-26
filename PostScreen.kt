package com.example.postapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.postapp.data.models.Post
import com.example.postapp.viewmodel.PostViewModel

@Composable
fun PostScreen(viewModel: PostViewModel) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var editingPostId by remember { mutableStateOf<Int?>(null) }

    val posts = viewModel.post

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Conteúdo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (editingPostId != null) {
                    viewModel.updatePost(editingPostId!!, title, content)
                    editingPostId = null
                } else {
                    viewModel.createPost(title, content, {}, {})
                }
                title = ""
                content = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (editingPostId != null) "Atualizar Post" else "Criar Post")
        }

        Spacer(modifier = Modifier.height(16.dp))

        posts.forEach { post ->
            PostItem(
                post = post,
                onDelete = { id -> viewModel.deletePost(id) },
                onEdit = {
                    editingPostId = it.id
                    title = it.title
                    content = it.content
                }
            )
        }
    }
}
