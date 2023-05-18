package ru.rsue.ostapenko.book_dj.publisher

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
import ru.rsue.ostapenko.book_dj.api.Connection.publishersApi
import kotlin.system.exitProcess

// Контроллер, взаимодействующий с объектами модели и представления
class PublisherFragment : Fragment() {
    companion object {
        private const val ARG_PUBLISHER_ID = "publisher_id"

        fun newInstance(publisherId: Int) =
            PublisherFragment().apply {
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
        super. onCreate(savedInstanceState)
        val publisherId = requireArguments().getSerializable(ARG_PUBLISHER_ID) as Int
        publisher = Connection.publishers.find { publishers -> publishers.id == publisherId }
    }

    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.activity_update_publisher, container, false)

        id_update = v.findViewById(R.id.id_update_publisher)
        var updateId = publisher?.id.toString()
        id_update.setText(updateId)

        namePublisher_update = v.findViewById(R.id.namePublisher_update)
        namePublisher_update.setText(publisher?.namePublisher)
        namePublisher_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int) {
                publisher?.namePublisher = s.toString()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        address_update = v.findViewById(R.id.address_update)
        address_update.setText(publisher?.address)
        address_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int) {
                publisher?.address = s.toString()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        site_update = v.findViewById(R.id.site_update)
        site_update.setText(publisher?.site)
        site_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int) {
                publisher?.site = s.toString()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })

        v.findViewById<Button>(R.id.delete_button).setOnClickListener {
            GlobalScope.launch {
                publishersApi.deletePublisher(id_update.text.toString().toInt()).enqueue(object :
                    Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        println("передано")
                        GlobalScope.launch {
                            Connection.updatePublishers()
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