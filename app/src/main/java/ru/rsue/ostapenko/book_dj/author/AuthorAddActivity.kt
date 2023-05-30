package ru.rsue.ostapenko.book_dj.author

import android.content.Intent
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
import ru.rsue.ostapenko.book_dj.MainActivity
import ru.rsue.ostapenko.book_dj.R
import ru.rsue.ostapenko.book_dj.api.Connection
import ru.rsue.ostapenko.book_dj.api.Connection.authorsApi
import ru.rsue.ostapenko.book_dj.auth.Token


class AuthorAddActivity : AppCompatActivity() {
    lateinit var firstName_input: EditText
    lateinit var lastName_input: EditText

    lateinit var add_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_author)

        firstName_input = findViewById(R.id.firstName_input)
        lastName_input = findViewById(R.id.lastName_input)
        add_button = findViewById(R.id.add_button)

        // Асинхронная передача значения на сервер
        add_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                authorsApi.postAuthor(Token.tokenHeader(), getAuthor())
                    .enqueue(object : Callback<Authors> {
                        override fun onResponse(call: Call<Authors>, response: Response<Authors>) {
                            println("Передано")
                            GlobalScope.launch {
                                Connection.updateAuthors()
                                startActivity(
                                    Intent(
                                        this@AuthorAddActivity,
                                        MainActivity::class.java
                                    )
                                )
                            }
                        }
                        override fun onFailure(call: Call<Authors>, t: Throwable) {
                            println("Ошибка")
                            t.printStackTrace()
                        }
                    })
            }
        })
    }

    fun getAuthor(): Authors {
        return Authors(
            0,
            firstName_input.text.toString(),
            lastName_input.text.toString()
        )
    }
}