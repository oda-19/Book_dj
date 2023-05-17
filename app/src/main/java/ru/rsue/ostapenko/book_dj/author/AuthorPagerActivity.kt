package ru.rsue.ostapenko.book_dj.author

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
class AuthorPagerActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var authors: List<Authors>

    companion object {
        private const val EXTRA_AUTHOR_ID =
            "ru.rsue.android.ostapenko.book_dj.author_id"
        fun newIntent(packageContext: Context?, authorId: Int?) = Intent(
            packageContext,
            AuthorPagerActivity::class.java
        ).apply {
            putExtra(EXTRA_AUTHOR_ID, authorId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager_author)
        val authorId = intent
            .getSerializableExtra(EXTRA_AUTHOR_ID) as Int?

        viewPager = findViewById(R.id.activity_pager_author_view_pager)
        viewPager.adapter = ViewPagerAdapter(this)

        authors = Connection.authors
        for (i in authors.indices)
            if (authors[i].id == authorId) {
                viewPager.currentItem = i
                break
            }

    }

    private class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        private val authors: List<Authors> = Connection.authors
        override fun getItemCount() = authors.size
        override fun createFragment(position: Int) =
            AuthorFragment.newInstance(authors[position].id)
    }

}