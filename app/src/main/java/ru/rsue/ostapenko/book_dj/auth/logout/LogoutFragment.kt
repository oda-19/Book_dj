package ru.rsue.ostapenko.book_dj.auth.logout

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.rsue.ostapenko.book_dj.MainActivity
import ru.rsue.ostapenko.book_dj.R
import ru.rsue.ostapenko.book_dj.api.Connection
import ru.rsue.ostapenko.book_dj.auth.Token


class LogoutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.logout_fragment, container, false)

        Token.TOKEN = Token("", "")
        Connection.update()
        activity?.startActivity(
            Intent(activity, MainActivity::class.java)
        )

        return v
    }
}