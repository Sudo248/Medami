package com.sudo.data.repositories.user

import android.util.Log
import com.sudo.data.mapper.toUserAccountFB
import com.sudo.data.remote.firebase.service.FirebaseService
import com.sudo.domain.common.Result
import com.sudo.domain.entities.Prescription
import com.sudo.domain.entities.User
import com.sudo.domain.entities.UserAccount
import com.sudo.domain.repositories.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImplFB @Inject constructor(
    private val firebaseService: FirebaseService
    ) : UserRepository {
    companion object val TAG = "UserRepositoryImplFB"

    override suspend fun logIn(account: UserAccount): Flow<Result<User>> = flow{
        emit(Result.Loading)
        firebaseService.signIn(account.toUserAccountFB()).collect {
            when(it){
                is Result.Error -> emit(it)
                is Result.Success -> {
                    Log.d(TAG, "logIn: Success")
                }
                else -> emit(Result.Error("Lỗi không xác định."))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun registerAccount(account: UserAccount): Flow<Result<UserAccount>> = flow{
        emit(Result.Loading)
        firebaseService.register(account.toUserAccountFB()).collect {
            when(it){
                is Result.Error -> emit(it)
                is Result.Success -> {
                    Log.d(TAG, "registerAccount: success")
                }
                else -> emit(Result.Error("Lỗi không xác định."))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateAccount(account: UserAccount): Flow<Result<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun logOut(account: UserAccount): Flow<Result<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User): Flow<Result<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun addUser(user: User): Flow<Result<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPrescriptionsOfUser(user: User): Flow<Result<List<Prescription>>> {
        TODO("Not yet implemented")
    }



}