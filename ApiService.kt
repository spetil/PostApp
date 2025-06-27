package com.example.postapp.data

import com.example.postapp.data.models.*
import retrofit2.http.*

interface ApiService {
    @POST("users/")
    suspend fun createUser(@Body user: UserCreateRequest): User

    @GET("users/")
    suspend fun getUsers(): List<User>

    @POST("users/{user_id}/posts/")
    suspend fun createPost(@Path("user_id") userId: Int, @Body post: CreatePostRequest): Post

    @GET("users/{user_id}/posts/")
    suspend fun getPosts(@Path("user_id") userId: Int): List<Post>

    @PUT("posts/{post_id}/")
    suspend fun updatePost(@Path("post_id") postId: Int, @Body post: CreatePostRequest): Post

    @DELETE("posts/{post_id}/")
    suspend fun deletePost(@Path("post_id") postId: Int)
}
