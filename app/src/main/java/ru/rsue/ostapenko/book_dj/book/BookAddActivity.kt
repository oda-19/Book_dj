package ru.rsue.ostapenko.book_dj.book

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import ru.rsue.ostapenko.book_dj.R


class BookAddActivity : AppCompatActivity() {
    lateinit var code_input : EditText
    lateinit var title_input : EditText
    lateinit var authorId_input : EditText
    lateinit var publishId_input : EditText
    lateinit var yearPublish_input : EditText
    lateinit var countPage_input : EditText
    lateinit var hardcover_input : EditText
    lateinit var abstract_input : EditText
    lateinit var status_input : CheckBox
    lateinit var add_button : Button

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
                /*val book = Book()
                BookLab.get(requireActivity()).addBook(book)
                val intent =
                    BookPagerActivity.newIntent(requireActivity(),
                        book.id)
                startActivity(intent)
                true*/

            }
        })
    }
}