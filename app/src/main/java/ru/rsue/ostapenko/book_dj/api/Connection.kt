package ru.rsue.ostapenko.book_dj.api

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.rsue.ostapenko.book_dj.auth.token.Token
import ru.rsue.ostapenko.book_dj.author.Authors
import ru.rsue.ostapenko.book_dj.book.Books
import ru.rsue.ostapenko.book_dj.publisher.Publishers


object Connection {
    var books: List<Books> = emptyList()
    var authors: List<Authors> = emptyList()
    var publishers: List<Publishers> = emptyList()

    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val okHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(logging)
    }
    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())
        .build()


    val booksApi = retrofit.create(BooksApi::class.java)
    val authorsApi = retrofit.create(AuthorsApi::class.java)
    val publishersApi = retrofit.create(PublishersApi::class.java)
    val authApi = retrofit.create(UserAuthApi::class.java)

    fun updateBooks(): List<Books> {
        books = booksApi.getBooks(Token.tokenHeader()).execute().body() ?: emptyList<Books>()
        return books
    }

    fun updateAuthors(): List<Authors> {
        authors = authorsApi.getAuthors(Token.tokenHeader()).execute().body() ?: emptyList<Authors>()
        return authors
    }

    fun updatePublishers(): List<Publishers> {
        publishers = publishersApi.getPublishers(Token.tokenHeader()).execute().body() ?: emptyList<Publishers>()
        return publishers
    }

    fun authorsBeauty() =
        authors.filter { authors -> authors.id >= 0 }

    fun publisherBeauty() =
        publishers.filter { publishers -> publishers.id >= 0 }

    fun update() {
        GlobalScope.launch {
            updateAuthors()
            updateBooks()
            updatePublishers()
        }
    }
}