package ru.rsue.ostapenko.book_dj.auth.reg

import androidx.fragment.app.Fragment
import ru.rsue.ostapenko.book_dj.SingleFragmentActivity


class RegActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment = RegFragment()
}