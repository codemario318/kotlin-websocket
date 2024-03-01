package com.mario.websocket.chat

data class ChatNotification(
    val id: String,
    val senderId: String,
    val recipientId: String,
    val content: String,
)