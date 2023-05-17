package ru.rsue.ostapenko.book_dj.author

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rsue.ostapenko.book_dj.api.Connection
import ru.rsue.ostapenko.book_dj.api.Connection.authorsApi
import ru.rsue.ostapenko.book_dj.api.Connection.booksApi
import ru.rsue.ostapenko.book_dj.book.Books

// Передача данных между классами-контроллерами
class AuthorLab(context: Context): ViewModel() {
    val authors = mutableListOf<Authors>()
    fun getAuthor(id: Int): Authors? {
        for (author in authors) {
            if (author.id == id) {
                return author
            }
        }
        return null
    }

    fun updateList() {
        viewModelScope.launch(Dispatchers.IO) {
            authors.clear()
            authors.addAll(Connection.updateAuthors())
        }
    }

    init {
        authors.clear()
        authors.addAll(Connection.authors)
    }
}