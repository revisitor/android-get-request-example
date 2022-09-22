package ru.mtrefelov.http

import android.util.Log

import java.net.URL
import javax.net.ssl.HttpsURLConnection

class LogResponseRunnable(private val url: String) : Runnable {
    override fun run() = with(connect()) {
        val response: String = inputStream.bufferedReader().use { it.readText() }
        Log.d("Flickr cats", response)
        disconnect()
    }

    private fun connect(): HttpsURLConnection {
        val connection = URL(url).openConnection() as HttpsURLConnection
        return connection.apply {
            requestMethod = "GET"
            doInput = true
            connectTimeout = 3000
        }
    }
}