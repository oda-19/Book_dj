package ru.rsue.ostapenko.book_dj.api

import retrofit2.Call
import retrofit2.http.*
import ru.rsue.ostapenko.book_dj.book.Books


interface BooksApi {
    @GET("api/books/")
    fun getBooks(
        @Header("Authorization") token: String
    ): Call<List<Books>>

    @POST("api/books/")
    fun postBook(
        @Header("Authorization") token: String
        , @Body books: Books
    ): Call<Books>

    @DELETE("api/books/{id}/")
    fun deleteBook(
        @Header("Authorization") token: String
        , @Path("id") bookId: Int
    ): Call<Unit>

    @PUT("api/books/{id}/")
    fun putBook(
        @Header("Authorization") token: String,
        @Path("id") bookId: Int,
        @Body books: Books
    ): Call<Unit>
}