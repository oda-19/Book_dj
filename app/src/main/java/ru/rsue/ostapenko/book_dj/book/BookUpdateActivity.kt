package ru.rsue.ostapenko.book_dj.book

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.rsue.ostapenko.book_dj.R
import ru.rsue.ostapenko.book_dj.api.Connection.booksApi


class BookUpdateActivity : AppCompatActivity() {
    lateinit var id_update: EditText
    lateinit var code_update: EditText
    lateinit var title_update: EditText
    lateinit var authorId_update: EditText
    lateinit var publishId_update: EditText
    lateinit var yearPublish_update: EditText
    lateinit var countPage_update: EditText
    lateinit var hardcover_update: EditText
    lateinit var abstract_update: EditText
    lateinit var status_update: CheckBox
    lateinit var update_button: Button
    lateinit var delete_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_book)
        id_update = findViewById(R.id.id_update_book)
        code_update = findViewById(R.id.code_update)
        title_update = findViewById(R.id.title_update)
        authorId_update = findViewById(R.id.authorId_update)
        publishId_update = findViewById(R.id.publishId_update)
        yearPublish_update = findViewById(R.id.yearPublish_update)
        countPage_update = findViewById(R.id.countPage_update)
        hardcover_update = findViewById(R.id.hardcover_update)
        abstract_update = findViewById(R.id.abstract_update)
        status_update = findViewById(R.id.status_update)
        update_button = findViewById(R.id.update_button)
        delete_button = findViewById(R.id.delete_button)

        //Set actionbar title after getAndSetIntentData method
        val ab: ActionBar? = supportActionBar
        if (ab != null) {
            ab.setTitle(title)
        }
        /*update_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //And only then we call this
                /*val myDB = MyDatabaseHelper(this@UpdateActivity)
                title = title_input.text.toString().trim { it <= ' ' }
                author = author_input.getText().toString().trim()
                pages = pages_input.getText().toString().trim()
                myDB.updateData(id, title, author, pages)*/
            }
        })
        delete_button.setOnClickListener {
            println("Успешно")
            GlobalScope.launch {
                booksApi.deleteBook(id_update.toString().toInt()).execute()
            }
        }*/
    }
}