package ru.rsue.ostapenko.book_dj.auth.token

data class Token(
    val refresh: String,
    val access: String
) {
    companion object {
        var TOKEN: Token = Token("", "")
        fun tokenHeader(): String = "Bearer ${TOKEN.access}"
    }
}