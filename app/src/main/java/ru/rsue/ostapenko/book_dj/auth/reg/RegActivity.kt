package ru.rsue.ostapenko.book_dj.auth.reg

import android.app.Activity
import androidx.fragment.app.Fragment
import ru.rsue.ostapenko.book_dj.SingleFragmentActivity
import ru.rsue.ostapenko.book_dj.book.BookListFragment

class RegActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment = RegFragment()
}