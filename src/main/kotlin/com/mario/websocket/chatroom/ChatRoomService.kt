package com.mario.websocket.chatroom

import org.springframework.stereotype.Service
import java.util.Optional

@Service
class ChatRoomService(
    private val chatRoomRepository: ChatRoomRepository
) {
    fun getChatRoomId(
        senderId: String,
        recipientId: String,
        createNewRoomIfNotExists: Boolean
    ): String {
        return chatRoomRepository
            .findBySenderIdAndRecipientId(senderId, recipientId)?.chatId
            ?: createChat(senderId, recipientId)
    }
    private fun createChat(senderId: String, recipientId: String): String {
        val chatId = "${senderId}_${recipientId}"
        val senderRecipient: ChatRoom = ChatRoom(
            chatId = chatId,
            senderId = senderId,
            recipientId = recipientId,
        )

        val recipientSender: ChatRoom = ChatRoom(
            chatId = chatId,
            senderId = senderId,
            recipientId = recipientId,
        )

        chatRoomRepository.save(senderRecipient)
        chatRoomRepository.save(recipientSender)

        return chatId
    }

}