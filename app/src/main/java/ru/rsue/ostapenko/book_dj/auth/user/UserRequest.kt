package ru.rsue.ostapenko.book_dj.auth.user

data class UserRequest(
    val username: String,
    val password: String
)