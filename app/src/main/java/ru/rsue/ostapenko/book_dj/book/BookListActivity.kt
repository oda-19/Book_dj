package ru.rsue.ostapenko.book_dj.book

import androidx.fragment.app.Fragment
import ru.rsue.ostapenko.book_dj.SingleFragmentActivity


// Выполняет ф-ции хоста для для BookListFragment (вывод списка)
class BookListActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment = BookListFragment()
}