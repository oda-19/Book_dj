package ru.rsue.ostapenko.book_dj.api

import retrofit2.Call
import retrofit2.http.*
import ru.rsue.ostapenko.book_dj.author.Authors


interface AuthorsApi {
    @GET("api/authors/")
    fun getAuthors(@Header("Authorization") token: String = "ASdasdasdasda"): Call<List<Authors>>

    @POST("api/authors/")
    fun postAuthor(@Body authors: Authors): Call<Authors>

    @DELETE("api/authors/{id}/")
    fun deleteAuthor(@Path("id") authorId: Int): Call<Unit>

    @PUT("api/authors/{id}/")
    fun putAuthor(@Path("id") authorId: Int, @Body authors: Authors): Call<Unit>
}