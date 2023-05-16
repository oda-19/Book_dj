package ru.rsue.ostapenko.book_dj.publisher

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import ru.rsue.ostapenko.book_dj.R


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