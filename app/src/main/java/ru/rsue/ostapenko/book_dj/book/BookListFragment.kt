package ru.rsue.ostapenko.book_dj.book

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.rsue.ostapenko.book_dj.R
import ru.rsue.ostapenko.book_dj.api.Connection

// Отображение данных в списке
class BookListFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var adapter: BookAdapter? = null
    lateinit var add_button: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(
            R.layout.fragment_list, container,
            false
        )
        recyclerView = view
            .findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        updateUI()

        add_button = view
            .findViewById(R.id.floatingActionButton_add)
        add_button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(context, BookAddActivity::class.java);
                startActivity(intent);
            }
        })
        return view
    }

    private fun updateUI() {
        val books = Connection.books
        adapter = BookAdapter(books)
        recyclerView!!.adapter = adapter
        if (adapter == null) {
            adapter = BookAdapter(books)
            recyclerView!!.adapter = adapter
        }
        else
            adapter!!.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private class BookHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!), View.OnClickListener {
        var book_id: TextView =
            itemView!!.findViewById(R.id.book_id)
        var book_title: TextView =
            itemView!!.findViewById(R.id.book_title)
        var book_author: TextView =
            itemView!!.findViewById(R.id.book_author)

        private lateinit var book: Books
        fun bindBook(book: Books) {
            this.book = book
            book_id.text = book.id.toString()
            book_title.text = book.title
            book_author.text = Connection.authors[book.authorId - 1].toString()
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context = v!!.context
            val intent = BookPagerActivity.newIntent(context, book.id)
            context.startActivity(intent)
        }

    }

    private class BookAdapter(books: List<Books>?) :
        RecyclerView.Adapter<BookHolder?>() {
        private var books: List<Books>? = null
        init {
            this.books = books
        }
        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): BookHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.item_book, parent, false)
            return BookHolder(view)
        }
        override fun onBindViewHolder(holder: BookHolder,
                                      position: Int) {
            val book = books!![position]
            holder.bindBook(book)
        }
        override fun getItemCount(): Int {
            return books!!.size
        }
    }

}