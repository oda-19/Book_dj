package ru.rsue.ostapenko.book_dj.book

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rsue.ostapenko.book_dj.api.Connection.booksApi
import java.util.*

class BookLab private constructor(context: Context) {
    val books = mutableListOf<Books>()

    companion object {
        private var INSTANCE: BookLab? = null
        fun get(context: Context): BookLab {
            if (INSTANCE == null)
                INSTANCE = BookLab(context)
            return INSTANCE!!
        }
    }

    fun getBook(id: Int): Books? {
        for (book in books) {
            if (book.id == id) {
                Thread.sleep(100)
                return book
            }
        }
        return null
    }

    fun addBook(book: Books){
        books.add(book)
    }

    init {
        booksApi.getBooks().enqueue(object : Callback<List<Books>> {
            override fun onResponse(call: Call<List<Books>>, response: Response<List<Books>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        books.addAll(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Books>>, t: Throwable) {
                println("Ошибка")
            }
        })

    }

}