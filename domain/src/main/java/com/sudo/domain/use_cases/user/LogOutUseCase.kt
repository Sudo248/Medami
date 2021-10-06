package com.sudo.domain.use_cases.user

import com.sudo.domain.entities.UserAccount
import com.sudo.domain.repositories.user.UserRepository

class LogOutUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(account: UserAccount) = repository.logOut(account)
}