package com.jorra.ledcontrol

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val url = "http://192.168.1.140/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonOn = findViewById<Button>(R.id.button1).apply {
            setOnClickListener {
                sendRequest("on")
            }
        }

        val buttonMin = findViewById<Button>(R.id.button2).apply {
            setOnClickListener {
                sendRequest("low")
            }
        }

        val buttonMed = findViewById<Button>(R.id.button3).apply {
            setOnClickListener {
                sendRequest("med")
            }
        }

        val buttonMax = findViewById<Button>(R.id.button4).apply {
            setOnClickListener {
                sendRequest("max")
            }
        }

    }

    private fun sendRequest(route: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val url = URL("${url}${route}")
            val urlConnection = url.openConnection() as HttpURLConnection
            try {
                urlConnection.inputStream.use { it.readBytes() }
            } finally {
                urlConnection.disconnect()
            }
        }
    }
}