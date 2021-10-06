package com.sudo.data.repositories.user

import com.google.firebase.auth.FirebaseAuth
import com.sudo.data.remote.firebase.models.UserAccountFB
import com.sudo.data.utils.toUserAccount
import com.sudo.domain.common.Result
import com.sudo.domain.entities.Prescription
import com.sudo.domain.entities.User
import com.sudo.domain.entities.UserAccount
import com.sudo.domain.repositories.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImplFB @Inject constructor(
    private val auth: FirebaseAuth
    ) : UserRepository {
    override suspend fun logIn(account: UserAccount): Flow<Result<User>> = flow{
        emit(Result.Loading)
        try {
            var currentUser = auth.currentUser
            auth.signInWithEmailAndPassword(createFakeEmailOrNot(account.emailOrPhone), account.password).await()
            currentUser = auth.currentUser
            if(currentUser == null){
                emit(Result.Error("Không đăng nhập được"))
            }else{

            }
        }catch (e: Exception){
            emit(Result.Error(e.message!!))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun registerAccount(account: UserAccount): Flow<Result<UserAccount>> = flow{
        emit(Result.Loading)
        try{
            auth.createUserWithEmailAndPassword(createFakeEmailOrNot(account.emailOrPhone), account.password).await()
            val currentUser = auth.currentUser
            if(currentUser == null){
                emit(Result.Error("Không đăng kí được"))
            }else{
                val newAccount = UserAccountFB(account.emailOrPhone, account.password, currentUser.uid)
                emit(Result.Success(newAccount.toUserAccount()))
            }
        }catch (e :Exception){
            emit(Result.Error(e.message!!))
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

    private fun createFakeEmailOrNot(emailPhone: String): String{
        return if(emailPhone.matches(Regex("^[0-9]{10,13}$"))) {
            "$emailPhone@gmail.com"
        }else{
            emailPhone
        }
    }



}