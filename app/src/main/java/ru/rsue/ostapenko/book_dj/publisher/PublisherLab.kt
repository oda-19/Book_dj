package ru.rsue.ostapenko.book_dj.publisher

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rsue.ostapenko.book_dj.api.Connection.booksApi
import ru.rsue.ostapenko.book_dj.api.Connection.publishersApi

// Передача данных между классами-контроллерами
class PublisherLab private constructor(context: Context) {
    val publishers = mutableListOf<Publishers>()

    companion object {
        private var INSTANCE: PublisherLab? = null
        fun get(context: Context): PublisherLab {
            if (INSTANCE == null)
                INSTANCE = PublisherLab(context)
            return INSTANCE!!
        }
    }

    fun getPublisher(id: Int): Publishers? {
        for (publisher in publishers) {
            if (publisher.id == id) {
                Thread.sleep(100)
                return publisher
            }
        }
        return null
    }

    fun addPublisher(publisher: Publishers){
        publishers.add(publisher)
    }

    init {
        publishersApi.getPublishers().enqueue(object : Callback<List<Publishers>> {
            override fun onResponse(call: Call<List<Publishers>>, response: Response<List<Publishers>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        publishers.addAll(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Publishers>>, t: Throwable) {
                println("Ошибка")
            }
        })

    }

}