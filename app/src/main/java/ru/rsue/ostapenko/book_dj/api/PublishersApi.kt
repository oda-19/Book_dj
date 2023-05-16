package ru.rsue.ostapenko.book_dj.api

import retrofit2.Call
import retrofit2.http.GET
import ru.rsue.ostapenko.book_dj.book.Books
import ru.rsue.ostapenko.book_dj.publisher.Publishers

interface PublishersApi {
    @GET("api/publishers/")
    fun getPublishers(): Call<List<Publishers>>
}