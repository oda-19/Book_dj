package ru.rsue.ostapenko.book_dj.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.rsue.ostapenko.book_dj.book.Books

interface BooksApi {
    @GET("api/books/")
    fun getBooks(): Call<List<Books>>

    @POST("api/books/")
    fun postBook(@Body books: Books): Call<Books>

    @DELETE("api/books/{id}")
    fun deleteBook(@Path("id") bookId: Int): Call<Unit>
}