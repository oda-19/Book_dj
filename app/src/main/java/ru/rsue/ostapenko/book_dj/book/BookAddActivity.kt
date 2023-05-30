package ru.rsue.ostapenko.book_dj.book

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rsue.ostapenko.book_dj.R
import ru.rsue.ostapenko.book_dj.api.Connection
import ru.rsue.ostapenko.book_dj.api.Connection.booksApi
import ru.rsue.ostapenko.book_dj.auth.token.Token
import ru.rsue.ostapenko.book_dj.author.Authors
import ru.rsue.ostapenko.book_dj.publisher.Publishers
import kotlin.system.exitProcess


class BookAddActivity : AppCompatActivity() {
    lateinit var code_input: EditText
    lateinit var title_input: EditText
    lateinit var authorId_input: Spinner
    lateinit var publishId_input: Spinner
    lateinit var yearPublish_input: EditText
    lateinit var countPage_input: EditText
    lateinit var hardcover_input: EditText
    lateinit var abstract_input: EditText
    lateinit var status_input: CheckBox

    lateinit var add_button: Button

    lateinit var author_select: Authors
    lateinit var publish_select: Publishers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        code_input = findViewById(R.id.code_input)
        title_input = findViewById(R.id.title_input)
        authorId_input = findViewById(R.id.authorId_input)
        publishId_input = findViewById(R.id.publishId_input)
        yearPublish_input = findViewById(R.id.yearPublish_input)
        countPage_input = findViewById(R.id.countPage_input)
        hardcover_input = findViewById(R.id.hardcover_input)
        abstract_input = findViewById(R.id.abstract_input)
        status_input = findViewById(R.id.status_input)
        add_button = findViewById(R.id.add_button)

        // Асинхронная передача значения на сервер
        add_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                booksApi.postBook(Token.tokenHeader(), getBook()).enqueue(object : Callback<Books> {
                    override fun onResponse(call: Call<Books>, response: Response<Books>) {
                        println("Передано")
                        GlobalScope.launch {
                            Connection.updateBooks()
                            exitProcess(0)
                        }
                    }

                    override fun onFailure(call: Call<Books>, t: Throwable) {
                        println("Ошибка")
                        t.printStackTrace()
                    }
                })
            }
        })

        authorId_input.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, Connection.authorsBeauty())
                .apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        authorId_input.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    author_select = Connection.authorsBeauty()[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //
                }
            }

        publishId_input.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, Connection.publisherBeauty())
                .apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        publishId_input.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    publish_select = Connection.publisherBeauty()[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //
                }
            }
    }

    fun getBook(): Books {
        return Books(
            0,
            author_select.id,
            publish_select.id,
            title_input.text.toString(),
            code_input.text.toString(),
            yearPublish_input.text.toString().toInt(),
            countPage_input.text.toString().toInt(),
            hardcover_input.text.toString(),
            abstract_input.text.toString(),
            status_input.isChecked
        )
    }
}



