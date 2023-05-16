package ru.rsue.ostapenko.book_dj.author

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import ru.rsue.ostapenko.book_dj.R


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