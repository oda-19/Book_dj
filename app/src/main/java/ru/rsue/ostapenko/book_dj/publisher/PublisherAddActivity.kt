package ru.rsue.ostapenko.book_dj.publisher

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rsue.ostapenko.book_dj.R
import ru.rsue.ostapenko.book_dj.api.Connection
import ru.rsue.ostapenko.book_dj.api.Connection.publishersApi
import ru.rsue.ostapenko.book_dj.author.Authors
import kotlin.system.exitProcess


class PublisherAddActivity : AppCompatActivity() {
    lateinit var namePublisher_input : EditText
    lateinit var address_input : EditText
    lateinit var site_input : EditText
    lateinit var add_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_publisher)

        namePublisher_input = findViewById(R.id.namePublisher_input)
        address_input = findViewById(R.id.address_input)
        site_input = findViewById(R.id.site_input)
        add_button = findViewById(R.id.add_button)

        // Асинхронная передача значения на сервер
        add_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                Connection.publishersApi.postPublisher(addPublisher()).enqueue(object : Callback<Publishers> {
                    override fun onResponse(call: Call<Publishers>, response: Response<Publishers>) {
                        println("передано")
                        GlobalScope.launch {
                            Connection.updatePublishers()
                            exitProcess(0)
                        }
                    }

                    override fun onFailure(call: Call<Publishers>, t: Throwable) {
                        println("ошибка")
                        t.printStackTrace()
                    }

                })
            }
        })
    }

    fun addPublisher(): Publishers {
        return Publishers(0,
            namePublisher_input.text.toString(),
            address_input.text.toString(),
            site_input.text.toString()
        )
    }
}