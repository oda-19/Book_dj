package ru.rsue.ostapenko.book_dj.auth.reg

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rsue.ostapenko.book_dj.R
import ru.rsue.ostapenko.book_dj.api.Connection.authApi
import ru.rsue.ostapenko.book_dj.auth.user.User
import ru.rsue.ostapenko.book_dj.auth.login.LoginActivity


class RegFragment : Fragment() {
    lateinit var email: EditText
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var accept: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.activity_reg, container, false)

        email = v.findViewById(R.id.email_input)
        username = v.findViewById(R.id.login_input)
        password = v.findViewById(R.id.password_input)

        accept = v.findViewById(R.id.add_button)
        accept.setOnClickListener { registration() }

        v.findViewById<TextView>(R.id.login_text_button).setOnClickListener {
            activity?.startActivity(
                Intent(activity, LoginActivity::class.java)
            )
        }

        return v
    }

    fun registration() {
        val vEmail = email.text.toString()
        val vUsername = username.text.toString()
        val vPassword = password.text.toString()

        GlobalScope.launch {
            authApi.registerUser(User(vEmail, vUsername, vPassword)).enqueue(
                object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        activity?.startActivity(
                            Intent(activity, LoginActivity::class.java)
                        )
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(context, "Error. Try later!", Toast.LENGTH_SHORT)
                    }
                }
            )
        }
    }
}