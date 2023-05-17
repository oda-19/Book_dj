package ru.rsue.ostapenko.book_dj.author

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rsue.ostapenko.book_dj.api.Connection.authorsApi
import ru.rsue.ostapenko.book_dj.api.Connection.booksApi
import ru.rsue.ostapenko.book_dj.book.Books

// Передача данных между классами-контроллерами
class AuthorLab private constructor(context: Context) {
    val authors = mutableListOf<Authors>()

    companion object {
        private var INSTANCE: AuthorLab? = null
        fun get(context: Context): AuthorLab {
            if (INSTANCE == null)
                INSTANCE = AuthorLab(context)
            return INSTANCE!!
        }
    }

    fun getAuthor(id: Int): Authors? {
        for (author in authors) {
            if (author.id == id) {
                Thread.sleep(100)
                return author
            }
        }
        return null
    }

    fun addAuthor(author: Authors){
        authors.add(author)
    }

    init {
        authorsApi.getAuthors().enqueue(object : Callback<List<Authors>> {
            override fun onResponse(call: Call<List<Authors>>, response: Response<List<Authors>>) {
                if (response.isSuccessful) {
                    authors.clear()
                    response.body()?.let {
                        authors.addAll(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Authors>>, t: Throwable) {
                println("Ошибка")
            }
        })

    }

}