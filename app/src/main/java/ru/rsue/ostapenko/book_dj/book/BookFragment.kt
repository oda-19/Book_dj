package ru.rsue.ostapenko.book_dj.book

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rsue.ostapenko.book_dj.R
import ru.rsue.ostapenko.book_dj.api.Connection
import ru.rsue.ostapenko.book_dj.api.Connection.booksApi
import ru.rsue.ostapenko.book_dj.author.Authors
import kotlin.system.exitProcess

// Контроллер, взаимодействующий с объектами модели и представления
class BookFragment : Fragment() {
    companion object {
        private const val ARG_BOOK_ID = "book_id"

        fun newInstance(bookId: Int) =
            BookFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_BOOK_ID, bookId)
                }
            }
    }

    private var book: Books? = null
    private lateinit var id_update: EditText
    private lateinit var code_update: EditText
    private lateinit var title_update: EditText
    private lateinit var authorId_update: EditText
    private lateinit var publishId_update: EditText
    private lateinit var yearPublish_update: EditText
    private lateinit var countPage_update: EditText
    private lateinit var hardcover_update: EditText
    private lateinit var abstract_update: EditText
    private lateinit var status_update: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bookId = requireArguments().getSerializable(ARG_BOOK_ID) as Int
        book = Connection.books.find { books -> books.id == bookId }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.activity_update_book, container, false)

        id_update = v.findViewById(R.id.id_update_book)
        var updateId = book?.id.toString()
        id_update.setText(updateId)

        code_update = v.findViewById(R.id.code_update)
        code_update.setText(book?.code)
        code_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int
            ) {
                book?.code = s.toString()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        title_update = v.findViewById(R.id.title_update)
        title_update.setText(book?.title)
        title_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int
            ) {
                book?.title = s.toString()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        authorId_update = v.findViewById(R.id.authorId_update)
        var authorId = book?.authorId.toString()
        authorId_update.setText(authorId)
        authorId_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int
            ) {
                book?.authorId = s.toString().toInt()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        publishId_update = v.findViewById(R.id.publishId_update)
        var publishId = book?.publishId.toString()
        publishId_update.setText(publishId)
        publishId_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int
            ) {
                book?.publishId = s.toString().toInt()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        yearPublish_update = v.findViewById(R.id.yearPublish_update)
        var yearPublish = book?.yearPublish.toString()
        yearPublish_update.setText(yearPublish)
        yearPublish_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int
            ) {
                book?.yearPublish = s.toString().toInt()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        countPage_update = v.findViewById(R.id.countPage_update)
        var countPage = book?.countPage.toString()
        countPage_update.setText(countPage)
        countPage_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int
            ) {
                book?.countPage = s.toString().toInt()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        hardcover_update = v.findViewById(R.id.hardcover_update)
        hardcover_update.setText(book?.hardcover)
        hardcover_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int
            ) {
                book?.hardcover = s.toString()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        abstract_update = v.findViewById(R.id.abstract_update)
        abstract_update.setText(book?.abstract)
        abstract_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int
            ) {
                book?.abstract = s.toString()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        status_update = v.findViewById(R.id.status_update)
        status_update.setChecked(book?.status!!)
        status_update.setOnCheckedChangeListener { compoundButton: CompoundButton,
                                                   isChecked: Boolean ->
            // Назначение флага прочтения книги
            book?.status = isChecked
        }

        v.findViewById<Button>(R.id.delete_button).setOnClickListener {
            GlobalScope.launch {
                booksApi.deleteBook(id_update.text.toString().toInt()).enqueue(object :
                    Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        println("передано")
                        GlobalScope.launch {
                            Connection.updateBooks()
                            exitProcess(0)
                        }
                    }
                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        println("ошибка")
                        t.printStackTrace()
                    }
                })
            }
        }

        return v
    }

}