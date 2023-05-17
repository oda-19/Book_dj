package ru.rsue.ostapenko.book_dj.author

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import ru.rsue.ostapenko.book_dj.R


class AuthorUpdateActivity : AppCompatActivity() {
    lateinit var firstName_update: EditText
    lateinit var lastName_update: EditText
    lateinit var update_button: Button
    lateinit var delete_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_author)
        firstName_update = findViewById(R.id.firstName_update)
        lastName_update = findViewById(R.id.lastName_update)
        update_button = findViewById(R.id.update_button)
        delete_button = findViewById(R.id.delete_button)

        //Set actionbar title after getAndSetIntentData method
        val ab: ActionBar? = supportActionBar
        if (ab != null) {
            ab.setTitle(title)
        }
        update_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //And only then we call this
                /*val myDB = MyDatabaseHelper(this@UpdateActivity)
                title = title_input.text.toString().trim { it <= ' ' }
                author = author_input.getText().toString().trim()
                pages = pages_input.getText().toString().trim()
                myDB.updateData(id, title, author, pages)*/
            }
        })
        delete_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

            }
        })
    }
}