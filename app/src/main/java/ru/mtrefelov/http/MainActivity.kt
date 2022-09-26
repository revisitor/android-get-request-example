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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val httpTask = LogHttpResponseRunnable(url)
        val buttonFetchHttp: Button = findViewById(R.id.button_http)
        buttonFetchHttp.setOnClickListener {
            httpRequestsExecutor.execute(httpTask)
        }

        val okHttpTask = LogOkHttpResponse(url)
        val buttonFetchOkHttp: Button = findViewById(R.id.button_okhttp)
        buttonFetchOkHttp.setOnClickListener {
            okHttpTask.run()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        httpRequestsExecutor.shutdown()
    }
}