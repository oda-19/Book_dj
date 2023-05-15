package ru.rsue.ostapenko.book_dj.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Connection {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val booksApi = retrofit.create(BooksApi::class.java)
}