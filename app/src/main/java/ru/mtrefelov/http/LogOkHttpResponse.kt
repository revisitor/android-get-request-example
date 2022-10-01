package ru.mtrefelov.http

import android.util.Log
import okhttp3.*
import java.io.IOException

class LogOkHttpResponse(url: String) {
    private val client = OkHttpClient()
    private val request = Request.Builder().url(url).build()

    fun run() = client.newCall(request).enqueue(ResponseCallback)

    private object ResponseCallback : Callback {
        override fun onResponse(call: Call, response: Response) {
            response.body?.use {
                Log.i("Flickr OkCats", it.string())
            }
        }

        override fun onFailure(call: Call, e: IOException) {
            Log.e("LogOkHttpResponse", e.stackTraceToString())
        }
    }
}
