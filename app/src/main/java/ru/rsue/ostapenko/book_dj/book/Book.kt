package ru.rsue.ostapenko.book_dj.book

import java.util.*

import java.util.Date

data class Book(
    var id: Int = -1,
    var author: String = "",
    var publish: String = "",
    var title: String  = "",
    var code: String = "",
    var yearPublish: Int = 0,
    var countPage: Int = 0,
    var hardcover: String = "",
    var abstract: String = "",
    var status: Boolean = false
)
