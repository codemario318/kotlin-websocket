package com.mario.websocket.user

import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository: MongoRepository<User, String> {
    fun findAllByStatus(status: Status): List<User>
}
