package ru.rsue.ostapenko.book_dj.api

import retrofit2.Call
import retrofit2.http.GET
import ru.rsue.ostapenko.book_dj.book.Book

interface BooksApi {
    @GET("books/")
    fun getBooks(): Call<List<Book>>
}