package ru.rsue.ostapenko.book_dj.api

import retrofit2.Call
import retrofit2.http.*
import ru.rsue.ostapenko.book_dj.auth.token.Token
import ru.rsue.ostapenko.book_dj.author.Authors


interface AuthorsApi {
    @GET("api/authors/")
    fun getAuthors(@Header("Authorization") token: String): Call<List<Authors>>

    @POST("api/authors/")
    fun postAuthor(@Header("Authorization") token: String, @Body authors: Authors): Call<Authors>

    @DELETE("api/authors/{id}/")
    fun deleteAuthor(@Header("Authorization") token: String, @Path("id") authorId: Int): Call<Unit>

    @PUT("api/authors/{id}/")
    fun putAuthor(@Header("Authorization") token: String, @Path("id") authorId: Int, @Body authors: Authors): Call<Unit>
}