package ru.rsue.ostapenko.book_dj.publisher

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rsue.ostapenko.book_dj.R
import ru.rsue.ostapenko.book_dj.api.Connection
import ru.rsue.ostapenko.book_dj.api.Connection.publishersApi
import ru.rsue.ostapenko.book_dj.author.Authors


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

        add_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                publishersApi.postPublisher(getPublisher()).enqueue(object : Callback<Publishers> {
                    override fun onResponse(call: Call<Publishers>, response: Response<Publishers>) {
                        println("передано")
                    }

                    override fun onFailure(call: Call<Publishers>, t: Throwable) {
                        println("ошибка")
                        t.printStackTrace()
                    }

                })

            }
        })
    }

    fun getPublisher(): Publishers {
        /*val list = listOf<Authors>()
        val id_author = list.size*/
        return Publishers(3,
            namePublisher_input.text.toString(),
            address_input.text.toString(),
            site_input.text.toString()
        )
    }
}