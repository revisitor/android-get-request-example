package ru.mtrefelov.http

import android.os.Bundle
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val url = "https://api.flickr.com/services/rest/" +
            "?method=flickr.photos.search" +
            "&api_key=ff49fcd4d4a08aa6aafb6ea3de826464" +
            "&tags=cat" +
            "&format=json" +
            "&nojsoncallback=1"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val task = LogResponseRunnable(url)
        val buttonFetch: Button = findViewById(R.id.button_http)
        buttonFetch.setOnClickListener {
            Thread(task).start()
        }
    }
}