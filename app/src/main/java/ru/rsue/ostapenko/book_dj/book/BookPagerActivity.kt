package ru.rsue.ostapenko.book_dj.book

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import ru.rsue.ostapenko.book_dj.R
import java.util.*

class BookPagerActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var books: List<Books>

    companion object {
        private const val EXTRA_BOOK_ID =
            "ru.rsue.android.ostapenko.book_dj.book_id"
        fun newIntent(packageContext: Context?, bookId: Int?) = Intent(
            packageContext,
            BookPagerActivity::class.java
        ).apply {
            putExtra(EXTRA_BOOK_ID, bookId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_pager)
        val bookId = intent
            .getSerializableExtra(EXTRA_BOOK_ID) as UUID?

        viewPager = findViewById(R.id.activity_book_pager_view_pager)
        viewPager.adapter = ViewPagerAdapter(this)

        books = BookLab.get(this).books
        for (i in books.indices)
            if (books[i].id == 0) {
                viewPager.currentItem = i
                break
            }

    }

    private class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        private val books: List<Books> =
            BookLab.get(fragmentActivity).books
        override fun getItemCount() = books.size
        override fun createFragment(position: Int) =
            BookFragment.newInstance(books[position].id)
    }

}