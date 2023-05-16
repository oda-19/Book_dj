package ru.rsue.ostapenko.book_dj.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.rsue.ostapenko.book_dj.author.Authors

object Connection {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val booksApi = retrofit.create(BooksApi::class.java)
    val authorsApi = retrofit.create(AuthorsApi::class.java)
    val publishersApi = retrofit.create(PublishersApi::class.java)

    /*fun authors(): List<Authors> {
        return authorsApi.getAuthors().execute().body()?: emptyList<Authors>()
    }*/
}