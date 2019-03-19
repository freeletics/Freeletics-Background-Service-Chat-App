package com.freeletics.tutorial.chatapp

sealed class Message {
    data class Text(val text: String) : Message()
    data class Image(val imageUrl: String) : Message()
}