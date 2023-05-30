package ru.rsue.ostapenko.book_dj.auth.logout

import androidx.fragment.app.Fragment
import ru.rsue.ostapenko.book_dj.SingleFragmentActivity


class LogoutActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment = LogoutFragment()
}