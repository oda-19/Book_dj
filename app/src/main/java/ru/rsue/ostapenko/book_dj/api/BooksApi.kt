package ru.rsue.ostapenko.book_dj.api

import retrofit2.Call
import retrofit2.http.GET
import ru.rsue.ostapenko.book_dj.book.Books

interface BooksApi {
    @GET("api/books/")
    fun getBooks(): Call<List<Books>>
}