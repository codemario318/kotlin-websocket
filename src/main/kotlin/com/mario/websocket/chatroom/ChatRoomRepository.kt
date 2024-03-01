package com.mario.websocket.chatroom

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface ChatRoomRepository: MongoRepository<ChatRoom, String> {
    fun findBySenderIdAndRecipientId(senderId: String, recipientId: String): ChatRoom?
}
