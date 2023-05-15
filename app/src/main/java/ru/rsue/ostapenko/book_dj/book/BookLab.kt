package ru.rsue.ostapenko.book_dj.book

import android.content.Context
import java.util.*

class BookLab private constructor(context: Context) {
    val books = mutableListOf<Book>()

    companion object {
        private var INSTANCE: BookLab? = null
        fun get(context: Context): BookLab {
            if (INSTANCE == null)
                INSTANCE = BookLab(context)
            return INSTANCE!!
        }
    }

    fun getBook(id: UUID): Book? {
        for (book in books) {
            if (book.id == id) {
                return book
            }
        }
        return null
    }

    fun addBook(book: Book){
        books.add(book)
    }

    init {
        for (i in 0..99) {
            val book = Book()
            book.title = "Book #$i"
            book.authorId = "Book #$i"
            books.add(book)
        }
    }

}