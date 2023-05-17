package ru.rsue.ostapenko.book_dj.author

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rsue.ostapenko.book_dj.R
import ru.rsue.ostapenko.book_dj.api.Connection.authorsApi
import kotlin.collections.List as List


class AuthorAddActivity : AppCompatActivity() {
    lateinit var firstName_input : EditText
    lateinit var lastName_input : EditText
    lateinit var add_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_author)

        firstName_input = findViewById(R.id.firstName_input)
        lastName_input = findViewById(R.id.lastName_input)
        add_button = findViewById(R.id.add_button)

        add_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                authorsApi.postAuthor(getAuthor()).enqueue(object : Callback<Authors> {
                    override fun onResponse(call: Call<Authors>, response: Response<Authors>) {
                        println("передано")
                    }

                    override fun onFailure(call: Call<Authors>, t: Throwable) {
                        println("ошибка")
                        t.printStackTrace()
                    }

                })

            }
        })
    }



    fun getAuthor(): Authors {
        /*val list = listOf<Authors>()
        val id_author = list.size*/
        return Authors(5,
            firstName_input.text.toString(),
            lastName_input.text.toString()
        )
    }

}