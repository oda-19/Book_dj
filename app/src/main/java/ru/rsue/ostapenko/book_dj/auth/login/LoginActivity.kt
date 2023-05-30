package ru.rsue.ostapenko.book_dj.auth.login

import androidx.fragment.app.Fragment
import ru.rsue.ostapenko.book_dj.SingleFragmentActivity


class LoginActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment = LoginFragment()
}