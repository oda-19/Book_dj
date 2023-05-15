package ru.rsue.ostapenko.book_dj.book

import java.util.*

class Book {
    var id: UUID
        private set
    var code = ""
    var title = ""
    var authorId = ""
    var publishId = ""
    var yearPublish = ""
    var countPage = ""
    var hardcover = ""
    var abstract = ""
    var status = false
    //Генерирование уникального идентификатора
    init {
        id = UUID.randomUUID()
    }
}