package com.mario.websocket.user

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class UserService(
    private val repository: UserRepository,
) {
    fun saveUser(user: User) {
        repository.save(user.copy(status = Status.ONLINE))
    }

    fun disconnect(user: User) {
        repository.findByIdOrNull(user.nickName)?.let { storedUser ->
            repository.save(storedUser.copy(status = Status.OFFLINE))
        } ?: throw IllegalStateException("잘못된 요청입니다.")
    }

    fun findConnectedUsers(): List<User> {
        return repository.findAllByStatus(Status.ONLINE);
    }
}