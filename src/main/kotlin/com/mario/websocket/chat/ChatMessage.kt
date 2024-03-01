package com.mario.websocket.chat

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import java.util.Date

data class ChatMessage(
    @Id val id: ObjectId = ObjectId(),
    val chatId: String,
    val senderId: String,
    val recipientId: String,
    val content: String,
    val timestamp: Date,
)