package ru.rsue.ostapenko.book_dj.publisher

// Модель Издательтсва
data class Publishers(
    var id: Int = -1,
    var namePublisher: String = "",
    var address: String = "",
    var site: String = ""


) {
    override fun toString(): String {
        return "$namePublisher"
    }
}
