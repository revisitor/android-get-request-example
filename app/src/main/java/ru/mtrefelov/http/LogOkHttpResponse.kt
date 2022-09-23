package ru.mtrefelov.http

import android.util.Log
import okhttp3.*
import java.io.IOException

class LogOkHttpResponse(url: String) {
    private val client = OkHttpClient()
    private val request = Request.Builder().url(url).build()

    fun run() = client.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            val responseBody = response.body?.string() ?: ""
            Log.i("Flickr OkCats", responseBody)
        }

        override fun onFailure(call: Call, e: IOException) {
            Log.e("LogOkHttpResponse", e.stackTraceToString())
        }
    })
}