package ru.rsue.ostapenko.book_dj.auth.login

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
import ru.rsue.ostapenko.book_dj.MainActivity
import ru.rsue.ostapenko.book_dj.R
import ru.rsue.ostapenko.book_dj.api.Connection
import ru.rsue.ostapenko.book_dj.auth.reg.RegActivity
import ru.rsue.ostapenko.book_dj.auth.token.Token
import ru.rsue.ostapenko.book_dj.auth.user.UserRequest

class LoginFragment : Fragment() {
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var accept: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.login_fragment, container, false)

        username = v.findViewById(R.id.login_input)
        password = v.findViewById(R.id.password_input)

        accept = v.findViewById(R.id.add_button)
        accept.setOnClickListener { login() }

        v.findViewById<TextView>(R.id.reg_text_button).setOnClickListener {
            activity?.startActivity(
                Intent(activity, RegActivity::class.java)
            )
        }

        return v
    }

    fun login() {
        val vUsername = username.text.toString()
        val vPassword = password.text.toString()

        GlobalScope.launch {
            Connection.authApi.loginUser(UserRequest(vUsername, vPassword)).enqueue(
                object : Callback<Token> {
                    override fun onResponse(call: Call<Token>, response: Response<Token>) {
                        Token.TOKEN = response.body() ?: Token.TOKEN
                        Connection.update()
                        activity?.startActivity(
                            Intent(activity, MainActivity::class.java)
                        )
                    }

                    override fun onFailure(call: Call<Token>, t: Throwable) {
                        Toast.makeText(context, "Error. Try later!", Toast.LENGTH_SHORT)
                    }

                }
            )
        }
    }
}