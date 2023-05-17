package ru.rsue.ostapenko.book_dj.book

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import ru.rsue.ostapenko.book_dj.R
import ru.rsue.ostapenko.book_dj.api.Connection

// Листание данных между активностями
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
        setContentView(R.layout.activity_pager_book)
        val bookId = intent
            .getSerializableExtra(EXTRA_BOOK_ID) as Int?

        viewPager = findViewById(R.id.activity_pager_book_view_pager)
        viewPager.adapter = ViewPagerAdapter(this)

        books = Connection.books
        for (i in books.indices)
            if (books[i].id == bookId) {
                viewPager.currentItem = i
                break
            }

    }

    private class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        private val books: List<Books> = Connection.books
        override fun getItemCount() = books.size
        override fun createFragment(position: Int) =
            BookFragment.newInstance(books[position].id)
    }

}