package ru.rsue.ostapenko.book_dj.author

import androidx.fragment.app.Fragment
import ru.rsue.ostapenko.book_dj.SingleFragmentActivity


// Выполняет ф-ции хоста для для BookListFragment (вывод списка)
class AuthorListActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment = AuthorListFragment()
}