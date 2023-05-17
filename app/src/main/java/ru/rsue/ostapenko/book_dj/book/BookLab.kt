package ru.rsue.ostapenko.book_dj.book

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rsue.ostapenko.book_dj.api.Connection
import ru.rsue.ostapenko.book_dj.api.Connection.booksApi
import java.util.*

// Передача данных между классами-контроллерами
class BookLab private constructor(context: Context): ViewModel() {
    val books = mutableListOf<Books>()

    companion object {
        private var INSTANCE: BookLab? = null
        fun get(context: Context): BookLab {
            if (INSTANCE == null)
                INSTANCE = BookLab(context)
            else
                INSTANCE!!.updateList()
            return INSTANCE!!
        }
    }

    fun getBook(id: Int): Books? {
        for (book in books) {
            if (book.id == id) {
                return book
            }
        }
        return null
    }

    fun updateList() {
        viewModelScope.launch(Dispatchers.IO) {
            books.clear()
            books.addAll(Connection.updateBooks())
        }
    }

    init {
        books.clear()
        books.addAll(Connection.books)
    }
}