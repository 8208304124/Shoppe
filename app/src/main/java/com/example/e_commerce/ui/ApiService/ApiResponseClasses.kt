package com.example.e_commerce.ui.ApiService

import retrofit2.Call
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

data class UserResponse(
    val status: Boolean,
    val user: User,
    val error: String
)
interface UserService {
    @POST("user/register")
    fun registerUser(@Body body: RequestBody): Call<UserResponse>
}

data class User(
    val email: String,
    val password: String,
    val mobile: String,
    val image: String,
    val createdAt: String,
    val _id: String,
    val __v: Int
)
