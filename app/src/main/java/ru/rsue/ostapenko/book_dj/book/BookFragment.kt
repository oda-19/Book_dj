package ru.rsue.ostapenko.book_dj.book

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
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


// Контроллер, взаимодействующий с объектами модели и представления
// Отображает инфу, которая содержится в экземпляре Book
class BookFragment : Fragment() {
    companion object {
        private const val ARG_BOOK_ID = "book_id"

        fun newInstance(bookId: Int) = BookFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_BOOK_ID, bookId)
            }
        }
    }

    private var book: Books? = null

    private lateinit var id_update: EditText
    private lateinit var code_update: EditText
    private lateinit var title_update: EditText
    private lateinit var authorId_update: Spinner
    private lateinit var publishId_update: Spinner
    private lateinit var yearPublish_update: EditText
    private lateinit var countPage_update: EditText
    private lateinit var hardcover_update: EditText
    private lateinit var abstract_update: EditText
    private lateinit var status_update: CheckBox

    lateinit var author_select: Authors
    lateinit var publish_select: Publishers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bookId = requireArguments().getSerializable(ARG_BOOK_ID) as Int
        book = Connection.books.find { books -> books.id == bookId }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.activity_update_book, container, false)

        id_update = v.findViewById(R.id.id_update_book)
        var updateId = book?.id.toString()
        id_update.setText(updateId)

        code_update = v.findViewById(R.id.code_update)
        code_update.setText(book?.code)
        code_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                book?.code = s.toString()
            }

            override fun afterTextChanged(c: Editable) {
                //
            }
        })

        title_update = v.findViewById(R.id.title_update)
        title_update.setText(book?.title)
        title_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                book?.title = s.toString()
            }

            override fun afterTextChanged(c: Editable) {
                //
            }
        })

        yearPublish_update = v.findViewById(R.id.yearPublish_update)
        var yearPublish = book?.yearPublish.toString()
        yearPublish_update.setText(yearPublish)
        yearPublish_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                book?.yearPublish = s.toString().toInt()
            }

            override fun afterTextChanged(c: Editable) {
                //
            }
        })

        countPage_update = v.findViewById(R.id.countPage_update)
        var countPage = book?.countPage.toString()
        countPage_update.setText(countPage)
        countPage_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                book?.countPage = s.toString().toInt()
            }

            override fun afterTextChanged(c: Editable) {
                //
            }
        })

        hardcover_update = v.findViewById(R.id.hardcover_update)
        hardcover_update.setText(book?.hardcover)
        hardcover_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                book?.hardcover = s.toString()
            }

            override fun afterTextChanged(c: Editable) {
                //
            }
        })

        abstract_update = v.findViewById(R.id.abstract_update)
        abstract_update.setText(book?.abstract)
        abstract_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                book?.abstract = s.toString()
            }

            override fun afterTextChanged(c: Editable) {
                //
            }
        })

        status_update = v.findViewById(R.id.status_update)
        status_update.setChecked(book?.status!!)
        status_update.setOnCheckedChangeListener { compoundButton: CompoundButton, isChecked: Boolean ->
            book?.status = isChecked
        }

        v.findViewById<Button>(R.id.delete_button).setOnClickListener {
            GlobalScope.launch {
                booksApi.deleteBook(Token.tokenHeader(), id_update.text.toString().toInt())
                    .enqueue(object : Callback<Unit> {
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            println("Передано")
                            GlobalScope.launch {
                                Connection.updateBooks()
                                exitProcess(0)
                            }
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            println("Ошибка")
                            t.printStackTrace()
                        }
                    })
            }
        }

        authorId_update = v.findViewById(R.id.authorId_update)
        publishId_update = v.findViewById(R.id.publishId_update)

        authorId_update.adapter =
            ArrayAdapter(
                this.requireContext(),
                android.R.layout.simple_spinner_item,
                Connection.authors
            )
                .apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        authorId_update.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    author_select = Connection.authors[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //
                }
            }

        publishId_update.adapter =
            ArrayAdapter(
                this.requireContext(),
                android.R.layout.simple_spinner_item,
                Connection.publishers
            )
                .apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        publishId_update.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    publish_select = Connection.publishers[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //
                }
            }

        v.findViewById<Button>(R.id.update_button).setOnClickListener {
            GlobalScope.launch {
                val (id, book) = getBook()
                booksApi.putBook(Token.tokenHeader(), id, book).enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        println("Передано")
                        GlobalScope.launch {
                            Connection.updateBooks()
                            exitProcess(0)
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        println("Ошибка")
                        t.printStackTrace()
                    }
                })
            }
        }

        var publishId = book?.publishId.toString().toInt()
        publishId_update.setSelection(Connection.publishers.indexOf(
            Connection.publishers.find { publishers -> publishers.id == publishId }
        ))

        var authorId = book?.authorId.toString().toInt()
        authorId_update.setSelection(Connection.authors.indexOf(
            Connection.authors.find { authors -> authors.id == authorId }
        ))

        return v
    }

    fun getBook(): Pair<Int, Books> {
        return id_update.text.toString().toInt() to
                Books(
                    id_update.text.toString().toInt(),
                    author_select.id,
                    publish_select.id,
                    title_update.text.toString(),
                    code_update.text.toString(),
                    yearPublish_update.text.toString().toInt(),
                    countPage_update.text.toString().toInt(),
                    hardcover_update.text.toString(),
                    abstract_update.text.toString(),
                    status_update.isChecked
                )
    }
}