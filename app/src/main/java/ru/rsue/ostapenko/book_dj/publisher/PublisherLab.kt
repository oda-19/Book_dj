package ru.rsue.ostapenko.book_dj.publisher

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rsue.ostapenko.book_dj.api.Connection
import ru.rsue.ostapenko.book_dj.api.Connection.booksApi
import ru.rsue.ostapenko.book_dj.api.Connection.publishersApi
import ru.rsue.ostapenko.book_dj.author.Authors

// Передача данных между классами-контроллерами
class PublisherLab(context: Context): ViewModel() {
    val publishers = mutableListOf<Publishers>()
    fun getPublisher(id: Int): Publishers? {
        for (publisher in publishers) {
            if (publisher.id == id) {
                return publisher
            }
        }
        return null
    }

    fun updateList() {
        viewModelScope.launch(Dispatchers.IO) {
            publishers.clear()
            publishers.addAll(Connection.updatePublishers())
        }
    }

    init {
        publishers.clear()
        publishers.addAll(Connection.publishers)
    }
}