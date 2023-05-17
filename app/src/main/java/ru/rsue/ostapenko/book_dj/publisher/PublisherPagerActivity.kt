package ru.rsue.ostapenko.book_dj.publisher

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
class PublisherPagerActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var publishers: List<Publishers>

    companion object {
        private const val EXTRA_PUBLISHER_ID =
            "ru.rsue.android.ostapenko.book_dj.publisher_id"
        fun newIntent(packageContext: Context?, publisherId: Int?) = Intent(
            packageContext,
            PublisherPagerActivity::class.java
        ).apply {
            putExtra(EXTRA_PUBLISHER_ID, publisherId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)
        val publisherId = intent
            .getSerializableExtra(EXTRA_PUBLISHER_ID) as Int?

        viewPager = findViewById(R.id.activity_pager_view_pager)
        viewPager.adapter = ViewPagerAdapter(this)

        publishers = Connection.publishers
        for (i in publishers.indices)
            if (publishers[i].id == publisherId) {
                viewPager.currentItem = i
                break
            }

    }

    private class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        private val publishers: List<Publishers> = Connection.publishers
        override fun getItemCount() = publishers.size
        override fun createFragment(position: Int) =
            PublisherFragment.newInstance(publishers[position].id)
    }

}