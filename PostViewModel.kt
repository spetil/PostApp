package com.example.postapp.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postapp.data.RetrofitInstance
import com.example.postapp.data.models.*
import kotlinx.coroutines.launch
import com.example.postapp.data.models.CreatePostRequest


class PostViewModel : ViewModel() {
    var post by mutableStateOf<List<Post>>(listOf())
    var users by mutableStateOf<List<User>>(listOf())
    private val userId = 1

    fun fetchUsers() {
        viewModelScope.launch {
            try { users = RetrofitInstance.api.getUsers() }
            catch (e: Exception) { e.printStackTrace() }
        }
    }

    fun fetchPosts() {
        viewModelScope.launch {
            try { post = RetrofitInstance.api.getPosts(userId) }
            catch (e: Exception) { e.printStackTrace() }
        }
    }

    fun createUser(name: String, email: String, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.createUser(UserCreateRequest(name, email))
                fetchUsers()
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
                onError()
            }
        }
    }

    fun createPost(title: String, content: String, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.createPost(userId, CreatePostRequest(title, content))
                fetchPosts()
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
                onError()
            }
        }
    }

    fun updatePost(postId: Int, title: String, content: String) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.updatePost(postId, CreatePostRequest(title, content))
                fetchPosts()
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    fun deletePost(postId: Int) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.deletePost(postId)
                fetchPosts()
            } catch (e: Exception) { e.printStackTrace() }
        }
    }
}
