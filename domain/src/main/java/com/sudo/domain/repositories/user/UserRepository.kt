package com.sudo.domain.repositories.user

import com.sudo.domain.entities.Prescription
import com.sudo.domain.entities.User
import com.sudo.domain.common.Result
import com.sudo.domain.entities.UserAccount
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    // login thành công trả về user.
    suspend fun logIn(account: UserAccount): Flow<Result<User>>
    // đăng kí thành công trả về 1 userAccount.
    suspend fun registerAccount(account: UserAccount): Flow<Result<UserAccount>>
    suspend fun updateAccount(account: UserAccount): Flow<Result<Boolean>>
    suspend fun logOut(account : UserAccount): Flow<Result<Boolean>>
    suspend fun updateUser(user: User): Flow<Result<Boolean>>
    suspend fun addUser(user: User): Flow<Result<Boolean>>
    suspend fun getPrescriptionsOfUser(user: User): Flow<Result<List<Prescription>>>
}