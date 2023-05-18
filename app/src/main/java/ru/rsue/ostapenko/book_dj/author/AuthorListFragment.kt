package ru.rsue.ostapenko.book_dj.author

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
class AuthorListFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var adapter: AuthorAdapter? = null
    lateinit var add_button: FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(activity)

        updateUI()

        add_button = view.findViewById(R.id.floatingActionButton_add)
        add_button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                val intent = Intent(context, AuthorAddActivity::class.java);
                startActivity(intent);
            }
        })

        return view
    }

    private fun updateUI() {
        val authors = Connection.authorsBeauty()
        adapter = AuthorAdapter(authors)
        recyclerView!!.adapter = adapter

        if (adapter == null) {
            adapter = AuthorAdapter(authors)
            recyclerView!!.adapter = adapter
        }
        else
            adapter!!.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private class AuthorHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!), View.OnClickListener {
        var author_id: TextView = itemView!!.findViewById(R.id.author_id)
        var author_firstName: TextView = itemView!!.findViewById(R.id.author_firstName)
        var author_lastName: TextView = itemView!!.findViewById(R.id.author_lastName)

        private lateinit var author: Authors

        fun bindAuthor(author: Authors) {
            this.author = author
            author_id.text = author.id.toString()
            author_firstName.text = author.firstName
            author_lastName.text = author.lastName
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context = v!!.context
            val intent = AuthorPagerActivity.newIntent(context, author.id)
            context.startActivity(intent)
        }
    }

    private class AuthorAdapter(authors: List<Authors>?) : RecyclerView.Adapter<AuthorHolder?>() {
        private var authors: List<Authors>? = null

        init {
            this.authors = authors
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_author, parent, false)

            return AuthorHolder(view)
        }

        override fun onBindViewHolder(holder: AuthorHolder, position: Int) {
            val author = authors!![position]
            holder.bindAuthor(author)
        }

        override fun getItemCount(): Int {
            return authors!!.size
        }
    }
}