package com.mario.websocket.chat

import com.mario.websocket.chatroom.ChatRoomService
import org.springframework.stereotype.Service

@Service
class ChatMessageService(
    private val repository: ChatMessageRepository,
    private val chatRoomService: ChatRoomService,
) {
    fun save(chatMessage: ChatMessage): ChatMessage {
        val chatId = chatRoomService.getChatRoomId(
            senderId = chatMessage.senderId,
            recipientId = chatMessage.recipientId,
            createNewRoomIfNotExists = true
        )

        return repository.save(chatMessage.copy(chatId = chatId))
    }

    fun findChatMessages(senderId: String, recipientId: String): List<ChatMessage> {
        val chatId = chatRoomService.getChatRoomId(
            senderId = senderId,
            recipientId = recipientId,
            createNewRoomIfNotExists = false
        )

        return repository.findByChatId(chatId)
    }
}