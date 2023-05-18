package ru.rsue.ostapenko.book_dj

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

// Обобщенный суперкласс
abstract class SingleFragmentActivity : AppCompatActivity() {
    protected abstract fun createFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all)

        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.fragmentContainer)

        if (fragment == null){
            fragment = createFragment()
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit()
        }
    }
}