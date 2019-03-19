package com.freeletics.tutorial.chatapp

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WebsocketClient {

    private val client = OkHttpClient()

    fun connect(messageListener: (message: String) -> Unit): (String) -> Unit {
        val request = Request.Builder()
            .url("${UploadClient.url}/ws")
            .build()

        val listener = object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                messageListener(text)
                println("Received $text")
            }
        }
        val ws = client.newWebSocket(request, listener)

        return { message -> ws.send(message) }
    }
}
