package ru.mtrefelov.http

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.*

class MainActivity : AppCompatActivity() {
    private val url = "https://api.flickr.com/services/rest/" +
            "?method=flickr.photos.search" +
            "&api_key=ff49fcd4d4a08aa6aafb6ea3de826464" +
            "&tags=cat" +
            "&format=json" +
            "&nojsoncallback=1"

    private val httpRequestsExecutor: ExecutorService = Executors.newCachedThreadPool()

    private lateinit var httpFetchButton: Button
    private lateinit var okHttpFetchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val httpTask = LogHttpResponseRunnable(url)
        httpFetchButton = findViewById(R.id.button_http)
        httpFetchButton.setOnClickListener {
            httpRequestsExecutor.execute(httpTask)
        }

        val okHttpTask = LogOkHttpResponse(url)
        okHttpFetchButton = findViewById(R.id.button_okhttp)
        okHttpFetchButton.setOnClickListener {
            okHttpTask.run()
        }
    }

    override fun onDestroy() {
        httpRequestsExecutor.shutdown()
        super.onDestroy()
    }
}