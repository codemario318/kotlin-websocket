package com.mario.websocket.user

import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class UserController(
    private val service: UserService
) {
    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    fun addUser(@Payload user: User): User {
        service.saveUser(user)
        return user
    }

    @MessageMapping("/user.disconnectUser")
    fun disconnect(@Payload user: User): User {
        service.disconnect(user);
        return user
    }

    @GetMapping("/users")
    fun findConnectedUsers(): ResponseEntity<List<User>> {
        return ResponseEntity.ok(service.findConnectedUsers())
    }
}