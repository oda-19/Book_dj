package ru.rsue.ostapenko.book_dj.book

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import androidx.fragment.app.Fragment
import ru.rsue.ostapenko.book_dj.R
import java.util.*

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
        super. onCreate(savedInstanceState)
        val bookId = requireArguments().getSerializable(ARG_BOOK_ID) as Int
        book = BookLab.get(requireActivity()).getBook(bookId)

    }

    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.activity_update_book, container, false)
        code_update = v.findViewById(R.id.code_update)
        code_update.setText(book?.code)
        code_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int) {
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
                s: CharSequence, start: Int, count: Int, after: Int) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int) {
                book?.title = s.toString()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        authorId_update = v.findViewById(R.id.authorId_update)
        authorId_update.setText(book?.author)
        authorId_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int) {
                book?.author = s.toString()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        publishId_update = v.findViewById(R.id.publishId_update)
        publishId_update.setText(book?.publish)
        publishId_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int) {
                book?.publish = s.toString()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        yearPublish_update = v.findViewById(R.id.yearPublish_update)
        book?.let { yearPublish_update.setText(it.yearPublish) }
        yearPublish_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int) {
                book?.yearPublish = s.toString().toInt()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        countPage_update = v.findViewById(R.id.countPage_update)
        countPage_update.setText(book?.countPage ?: 0)
        countPage_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int) {
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
                s: CharSequence, start: Int, count: Int, after: Int) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int) {
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
                s: CharSequence, start: Int, count: Int, after: Int) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int) {
                book?.abstract = s.toString()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        status_update = v.findViewById(R.id.status_update)
        status_update.setChecked(book?.status!!)
        status_update.setOnCheckedChangeListener{
                compoundButton: CompoundButton,
                isChecked: Boolean ->
            // Назначение флага прочтения книги
            book?.status = isChecked
        }

        return v
    }

}