package ru.rsue.ostapenko.book_dj.publisher

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
import ru.rsue.ostapenko.book_dj.api.Connection.publishersApi
import ru.rsue.ostapenko.book_dj.auth.token.Token
import kotlin.system.exitProcess


// Контроллер, взаимодействующий с объектами модели и представления
// Отображает инфу, которая содержится в экземпляре Publisher
class PublisherFragment : Fragment() {
    companion object {
        private const val ARG_PUBLISHER_ID = "publisher_id"

        fun newInstance(publisherId: Int) = PublisherFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_PUBLISHER_ID, publisherId)
            }
        }
    }

    private var publisher: Publishers? = null

    private lateinit var id_update: EditText
    private lateinit var namePublisher_update: EditText
    private lateinit var address_update: EditText
    private lateinit var site_update: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val publisherId = requireArguments().getSerializable(ARG_PUBLISHER_ID) as Int
        publisher = Connection.publishers.find { publishers -> publishers.id == publisherId }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container:
        ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.activity_update_publisher, container, false)

        id_update = v.findViewById(R.id.id_update_publisher)
        var updateId = publisher?.id.toString()
        id_update.setText(updateId)

        namePublisher_update = v.findViewById(R.id.namePublisher_update)
        namePublisher_update.setText(publisher?.namePublisher)
        namePublisher_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                publisher?.namePublisher = s.toString()
            }

            override fun afterTextChanged(c: Editable) {
                //
            }
        })

        address_update = v.findViewById(R.id.address_update)
        address_update.setText(publisher?.address)
        address_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                publisher?.address = s.toString()
            }

            override fun afterTextChanged(c: Editable) {
                //
            }
        })

        site_update = v.findViewById(R.id.site_update)
        site_update.setText(publisher?.site)
        site_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                publisher?.site = s.toString()
            }

            override fun afterTextChanged(c: Editable) {
                //
            }
        })

        v.findViewById<Button>(R.id.delete_button).setOnClickListener {
            GlobalScope.launch {
                publishersApi.deletePublisher(Token.tokenHeader(), id_update.text.toString().toInt())
                    .enqueue(object : Callback<Unit> {
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            println("Передано")
                            GlobalScope.launch {
                                Connection.updatePublishers()
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
                val (id, publisher) = getPublisher()
                publishersApi.putPublisher(Token.tokenHeader(), id, publisher).enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        println("Передано")
                        GlobalScope.launch {
                            Connection.updatePublishers()
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

    fun getPublisher(): Pair<Int, Publishers> {
        return id_update.text.toString().toInt() to
                Publishers(
                    id_update.text.toString().toInt(),
                    namePublisher_update.text.toString(),
                    address_update.text.toString(),
                    site_update.text.toString()
                )
    }
}