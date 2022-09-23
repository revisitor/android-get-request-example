package ru.mtrefelov.http

import android.util.Log
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class LogHttpResponseRunnable(rawURL: String) : Runnable {
    private val url = URL(rawURL)

    override fun run() {
        val connection = connect()
        val response: String = connection.inputStream.bufferedReader().use { it.readText() }
        Log.d("Flickr cats", response)
    }

    private fun connect(): HttpsURLConnection {
        val connection = url.openConnection() as HttpsURLConnection
        return connection.apply {
            requestMethod = "GET"
            doInput = true
            connectTimeout = 3000
        }
    }
}