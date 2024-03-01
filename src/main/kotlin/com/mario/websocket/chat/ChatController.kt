package com.mario.websocket.chat

import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class ChatController(
    private val chatMessageService: ChatMessageService,
    private val messagingTemplate: SimpMessagingTemplate
) {
    @MessageMapping("/chat")
    fun processMessage(
        @Payload chatMessage: ChatMessage
    ) {
        val savedMessage = chatMessageService.save(chatMessage)
        messagingTemplate.convertAndSendToUser(
            chatMessage.recipientId,
            "/queue/messages",
            ChatNotification(
                id = savedMessage.id.toString(),
                senderId = savedMessage.senderId,
                recipientId = savedMessage.recipientId,
                content = savedMessage.content
            )
        )
    }
    @GetMapping("/messages/{senderId}/{recipientId}")
    fun findChatMessages(
        @PathVariable("senderId") senderId: String,
        @PathVariable("recipientId") recipientId: String
    ): ResponseEntity<List<ChatMessage>> {
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId))
    }
}