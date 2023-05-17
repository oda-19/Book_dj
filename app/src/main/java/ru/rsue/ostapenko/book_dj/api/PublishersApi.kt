package ru.rsue.ostapenko.book_dj.api

import retrofit2.Call
import retrofit2.http.*
import ru.rsue.ostapenko.book_dj.book.Books
import ru.rsue.ostapenko.book_dj.publisher.Publishers

interface PublishersApi {
    @GET("api/publishers/")
    fun getPublishers(): Call<List<Publishers>>

    @POST("api/publishers/")
    fun postPublisher(@Body publishers: Publishers): Call<Publishers>

    @DELETE("api/publishers/{id}")
    fun deletePublisher(@Path("id") publisherId: Int): Call<Unit>
}