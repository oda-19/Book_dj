package ru.rsue.ostapenko.book_dj.author

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

// Контроллер, взаимодействующий с объектами модели и представления
class AuthorFragment : Fragment() {
    companion object {
        private const val ARG_AUTHOR_ID = "author_id"

        fun newInstance(author_id: Int) =
            AuthorFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_AUTHOR_ID, author_id)
                }
            }
    }

    private var author: Authors? = null
    private lateinit var firstName_update: EditText
    private lateinit var lastName_update: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super. onCreate(savedInstanceState)
        val authorId = requireArguments().getSerializable(ARG_AUTHOR_ID) as Int
        author = AuthorLab.get(requireActivity()).getAuthor(authorId)

    }

    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.activity_update_author, container, false)
        firstName_update = v.findViewById(R.id.firstName_update)
        firstName_update.setText(author?.firstName)
        firstName_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int) {
                author?.firstName = s.toString()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        lastName_update = v.findViewById(R.id.lastName_update)
        lastName_update.setText(author?.lastName)
        lastName_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int) {
                author?.lastName = s.toString()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        return v
    }

}