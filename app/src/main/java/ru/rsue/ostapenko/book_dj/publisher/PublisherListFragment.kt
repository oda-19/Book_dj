package ru.rsue.ostapenko.book_dj.publisher

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
class PublisherListFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var adapter: PublisherAdapter? = null
    lateinit var add_button: FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(activity)

        updateUI()

        add_button = view.findViewById(R.id.floatingActionButton_add)
        add_button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                val intent = Intent(context, PublisherAddActivity::class.java);
                startActivity(intent);
            }
        })

        return view
    }

    private fun updateUI() {
        val publishers = Connection.publisherBeauty()
        adapter = PublisherAdapter(publishers)
        recyclerView!!.adapter = adapter

        if (adapter == null) {
            adapter = PublisherAdapter(publishers)
            recyclerView!!.adapter = adapter
        }
        else
            adapter!!.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private class PublisherHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!), View.OnClickListener {
        var publisher_id: TextView = itemView!!.findViewById(R.id.publisher_id)
        var publisher_namePublisher: TextView = itemView!!.findViewById(R.id.publisher_namePublisher)
        var publisher_address: TextView = itemView!!.findViewById(R.id.publisher_address)

        private lateinit var publisher: Publishers

        fun bindPublisher(publisher: Publishers) {
            this.publisher = publisher
            publisher_id.text = publisher.id.toString()
            publisher_namePublisher.text = publisher.namePublisher
            publisher_address.text = publisher.address
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context = v!!.context
            val intent = PublisherPagerActivity.newIntent(context, publisher.id)
            context.startActivity(intent)
        }
    }

    private class PublisherAdapter(publishers: List<Publishers>?) : RecyclerView.Adapter<PublisherHolder?>() {
        private var publishers: List<Publishers>? = null

        init {
            this.publishers = publishers
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublisherHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_publisher, parent, false)

            return PublisherHolder(view)
        }

        override fun onBindViewHolder(holder: PublisherHolder, position: Int) {
            val publisher = publishers!![position]
            holder.bindPublisher(publisher)
        }

        override fun getItemCount(): Int {
            return publishers!!.size
        }
    }
}