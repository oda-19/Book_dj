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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rsue.ostapenko.book_dj.R
import ru.rsue.ostapenko.book_dj.api.Connection
import ru.rsue.ostapenko.book_dj.api.Connection.authorsApi
import ru.rsue.ostapenko.book_dj.api.Connection.booksApi
import ru.rsue.ostapenko.book_dj.api.Connection.publishersApi
import ru.rsue.ostapenko.book_dj.author.Authors
import ru.rsue.ostapenko.book_dj.publisher.Publishers


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

        add_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                /*booksApi.postBook(getBook()).enqueue(object : Callback<Books> {
                    override fun onResponse(call: Call<Books>, response: Response<Books>) {
                        println("передано")
                    }

                    override fun onFailure(call: Call<Books>, t: Throwable) {
                        println("ошибка")
                        t.printStackTrace()
                    }

                })*/
            }
        })
    }
}
        /*authorId_input.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Connection.authors())

        authorId_input.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                author_select = Connection.authors().get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        publishId_input.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                publishersApi.getPublishers().enqueue(object : Callback<List<Publishers>> {
                    override fun onResponse(call: Call<List<Publishers>>, response: Response<List<Publishers>>) {
                        if (response.isSuccessful) {
                            publish_select = response.body()?.get(p2)?: Publishers()
                        }
                    }

                    override fun onFailure(call: Call<List<Publishers>>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
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
    }*/



