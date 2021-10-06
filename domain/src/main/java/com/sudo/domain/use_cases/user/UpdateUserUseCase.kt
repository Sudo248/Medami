package com.sudo.domain.use_cases.user

import com.sudo.domain.entities.User
import com.sudo.domain.repositories.user.UserRepository

class UpdateUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(user: User) = repository.updateUser(user)
}