package ru.rsue.ostapenko.book_dj.book

// Модель Книги
data class Books(
    var id: Int = -1,
    var authorId: Int = 0,
    var publishId: Int = 0,
    var title: String  = "",
    var code: String = "",
    var yearPublish: Int = 0,
    var countPage: Int = 0,
    var hardcover: String = "",
    var abstract: String = "",
    var status: Boolean = false
)
