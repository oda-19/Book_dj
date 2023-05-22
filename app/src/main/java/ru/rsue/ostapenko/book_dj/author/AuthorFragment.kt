package ru.rsue.ostapenko.book_dj.author

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rsue.ostapenko.book_dj.R
import ru.rsue.ostapenko.book_dj.api.Connection
import ru.rsue.ostapenko.book_dj.api.Connection.authorsApi
import kotlin.system.exitProcess


// Контроллер, взаимодействующий с объектами модели и представления
// Отображает инфу, которая содержится в экземпляре Author
class AuthorFragment : Fragment() {
    companion object {
        private const val ARG_AUTHOR_ID = "author_id"

        fun newInstance(author_id: Int) = AuthorFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_AUTHOR_ID, author_id)
            }
        }
    }

    private var author: Authors? = null

    private lateinit var id_update: EditText
    private lateinit var firstName_update: EditText
    private lateinit var lastName_update: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authorId = requireArguments().getSerializable(ARG_AUTHOR_ID) as Int
        author = Connection.authors.find { authors -> authors.id == authorId }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.activity_update_author, container, false)

        id_update = v.findViewById(R.id.id_update_author)
        var updateId = author?.id.toString()
        id_update.setText(updateId)

        firstName_update = v.findViewById(R.id.firstName_update)
        firstName_update.setText(author?.firstName)
        firstName_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                author?.firstName = s.toString()
            }

            override fun afterTextChanged(c: Editable) {
                //
            }
        })

        lastName_update = v.findViewById(R.id.lastName_update)
        lastName_update.setText(author?.lastName)
        lastName_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                author?.lastName = s.toString()
            }

            override fun afterTextChanged(c: Editable) {
                //
            }
        })

        v.findViewById<Button>(R.id.delete_button).setOnClickListener {
            GlobalScope.launch {
                authorsApi.deleteAuthor(id_update.text.toString().toInt())
                    .enqueue(object : Callback<Unit> {
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            println("Передано")
                            GlobalScope.launch {
                                Connection.updateAuthors()
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

        v.findViewById<Button>(R.id.update_button).setOnClickListener {
            GlobalScope.launch {
                val (id, author) = getAuthor()
                authorsApi.putAuthor(id, author).enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        println("Передано")
                        GlobalScope.launch {
                            Connection.updateAuthors()
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

        return v
    }

    fun getAuthor(): Pair<Int, Authors> {
        return id_update.text.toString().toInt() to
                Authors(
                    id_update.text.toString().toInt(),
                    firstName_update.text.toString(),
                    lastName_update.text.toString()
                )
    }
}