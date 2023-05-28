package ru.rsue.ostapenko.book_dj.auth.token

data class Token(
    val refresh: String,
    val access: String
) {
    companion object {
        var TOKEN: Token = Token("", "")
        var TOKEN_HEADER = "Bearer ${TOKEN.access}"
    }
}