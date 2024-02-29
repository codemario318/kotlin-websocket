package com.mario.websocket.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    @Id val nickName: String,
    val fullName: String,
    val status: Status,
)
