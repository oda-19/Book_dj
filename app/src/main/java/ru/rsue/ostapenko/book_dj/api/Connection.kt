package ru.rsue.ostapenko.book_dj.api

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.rsue.ostapenko.book_dj.auth.token.Token
import ru.rsue.ostapenko.book_dj.author.Authors
import ru.rsue.ostapenko.book_dj.book.Books
import ru.rsue.ostapenko.book_dj.publisher.Publishers
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


object Connection {
    var books: List<Books> = emptyList()
    var authors: List<Authors> = emptyList()
    var publishers: List<Publishers> = emptyList()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8000/")
        .client(getUnsafeOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val booksApi = retrofit.create(BooksApi::class.java)
    val authorsApi = retrofit.create(AuthorsApi::class.java)
    val publishersApi = retrofit.create(PublishersApi::class.java)
    val authApi = retrofit.create(UserAuthApi::class.java)

    fun updateBooks(): List<Books> {
        books = booksApi.getBooks(Token.TOKEN_HEADER).execute().body() ?: emptyList<Books>()
        return books
    }
    fun updateAuthors(): List<Authors> {
        authors = authorsApi.getAuthors().execute().body() ?: emptyList<Authors>()
        return authors
    }
    fun updatePublishers(): List<Publishers> {
        publishers = publishersApi.getPublishers().execute().body() ?: emptyList<Publishers>()
        return publishers
    }

    fun authorsBeauty() =
        authors.filter { authors -> authors.id >= 0 }
    fun publisherBeauty() =
        publishers.filter { publishers -> publishers.id >= 0 }

    fun update() {
        updateAuthors()
        updateBooks()
        updatePublishers()
    }

    private fun getUnsafeOkHttpClient(): OkHttpClient {
        return try {
            // Create a trust manager that does not
            // validate certificate chains
            val trustAllCerts: Array<TrustManager> =
                arrayOf<TrustManager>(object : X509TrustManager {
                    @SuppressLint("TrustAllX509TrustManager")
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>,
                        authType: String?
                    ) {
                    }
                    @SuppressLint("TrustAllX509TrustManager")
                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>,
                        authType: String?
                    ) {
                    }
                    override fun getAcceptedIssuers():
                            Array<X509Certificate> =
                        arrayOf()
                })
            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory =
                sslContext.socketFactory
            val httpClient = OkHttpClient.Builder()
            httpClient.sslSocketFactory(sslSocketFactory,
                trustAllCerts[0] as X509TrustManager)
                .hostnameVerifier { hostname, session -> true }
                .build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}