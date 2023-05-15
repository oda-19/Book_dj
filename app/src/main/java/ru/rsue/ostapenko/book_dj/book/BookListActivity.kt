package ru.rsue.ostapenko.book_dj.book

import androidx.fragment.app.Fragment

class BookListActivity : BookSingleFragmentActivity() {
    override fun createFragment(): Fragment = BookListFragment()
}