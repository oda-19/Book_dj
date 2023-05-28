package ru.rsue.ostapenko.book_dj.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import ru.rsue.ostapenko.book_dj.auth.token.Token
import ru.rsue.ostapenko.book_dj.auth.user.User
import ru.rsue.ostapenko.book_dj.auth.user.UserRequest

interface UserAuthApi {
    @POST("auth/users/")
    fun registerUser(@Body request: User): Call<User>

    @POST("auth/token/")
    fun loginUser(@Body user: UserRequest): Call<Token>
}