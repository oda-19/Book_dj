package ru.rsue.ostapenko.book_dj.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.rsue.ostapenko.book_dj.author.Authors
import ru.rsue.ostapenko.book_dj.book.Books
import ru.rsue.ostapenko.book_dj.publisher.Publishers

object Connection {
    var authors: List<Authors> = emptyList()
    var books: List<Books> = emptyList()
    var publishers: List<Publishers> = emptyList()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val booksApi = retrofit.create(BooksApi::class.java)
    val authorsApi = retrofit.create(AuthorsApi::class.java)
    val publishersApi = retrofit.create(PublishersApi::class.java)

    fun updateAuthors(): List<Authors> {
        authors = authorsApi.getAuthors().execute().body()?: emptyList<Authors>()
        return authors
    }
    fun updateBooks(): List<Books> {
        books = booksApi.getBooks().execute().body()?: emptyList<Books>()
        return books
    }
    fun updatePublishers(): List<Publishers> {
        publishers = publishersApi.getPublishers().execute().body()?: emptyList<Publishers>()
        return publishers
    }

    fun update() {
        updateBooks()
        updatePublishers()
        updateAuthors()
    }
}