package ru.rsue.ostapenko.book_dj.author

// Модель Авторы
data class Authors(
    var id: Int = -1,
    var firstName: String = "",
    var lastName: String = ""
) {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}
