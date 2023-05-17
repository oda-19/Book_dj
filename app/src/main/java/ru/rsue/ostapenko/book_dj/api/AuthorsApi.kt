package ru.rsue.ostapenko.book_dj.api

import retrofit2.Call
import retrofit2.http.*
import ru.rsue.ostapenko.book_dj.author.Authors
import ru.rsue.ostapenko.book_dj.book.Books

interface AuthorsApi {
    @GET("api/authors/")
    fun getAuthors(): Call<List<Authors>>

    @POST("api/authors/")
    fun postAuthor(@Body authors: Authors): Call<Authors>

    @DELETE("api/authors/{id}/")
    fun deleteAuthor(@Path("id") authorId: Int): Call<Unit>
}