package com.mario.websocket.chatroom

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class ChatRoom(
    @Id val id: ObjectId = ObjectId(),
    val chatId: String,
    val senderId: String,
    val recipientId: String,
)