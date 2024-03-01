package com.mario.websocket.chat

import org.springframework.data.mongodb.repository.MongoRepository

interface ChatMessageRepository: MongoRepository<ChatMessage, String> {
    fun findByChatId(chatId: String): List<ChatMessage>
}
