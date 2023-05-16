package ru.rsue.ostapenko.book_dj.publisher

import androidx.fragment.app.Fragment
import ru.rsue.ostapenko.book_dj.SingleFragmentActivity

// Выполняет ф-ции хоста для для BookListFragment (вывод списка)
class PublisherListActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment = PublisherListFragment()
}