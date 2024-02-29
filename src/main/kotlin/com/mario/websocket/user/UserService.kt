package com.mario.websocket.user

import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository,
) {
    fun saveUser(user: User) {
        repository.save(User(
            nickName = user.nickName,
            fullName = user.fullName,
            status = Status.ONLINE,
        ))
    }

    fun disconnect(user: User) {
        val storedUser = repository.findById(user.nickName).orElse(null)

        if (storedUser != null) {
            repository.save(User(
                nickName = user.nickName,
                fullName = user.fullName,
                status = Status.OFFLINE,
            ))
        }
    }

    fun findConnectedUsers(): List<User> {
        return repository.findAllByStatus(Status.ONLINE);
    }
}