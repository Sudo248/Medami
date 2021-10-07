package com.sudo.data.repositories.user

import com.sudo.data.local.database.dao.MedamiDao
import com.sudo.data.mapper.toPrescription
import com.sudo.data.mapper.toUser
import com.sudo.data.mapper.toUserAccountDB
import com.sudo.data.mapper.toUserDB
import com.sudo.domain.entities.Prescription
import com.sudo.domain.entities.User
import com.sudo.domain.repositories.user.UserRepository
import com.sudo.domain.common.Result
import com.sudo.domain.entities.UserAccount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserRepositoryImplDB @Inject constructor(
    private val medamiDao: MedamiDao
) : UserRepository {
    override suspend fun logIn(account: UserAccount): Flow<Result<User>> = flow{
        emit(Result.Loading)
        try{
            val emailPhone = account.emailOrPhone
            val accountDB = medamiDao.getAccount(emailPhone)
            if(accountDB == null){
                emit(Result.Error("Tài khoản không tồn tại."))
            }
            else if(accountDB.password != account.password){
                emit(Result.Error("Sai mật khẩu."))
            }else{
                val userDB = medamiDao.getUserById(accountDB.userId)
                emit(Result.Success(userDB.toUser()))
            }
        }catch (e: Exception){
            emit(Result.Error(e.message!!))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun registerAccount(account: UserAccount): Flow<Result<UserAccount>> = flow{
        emit(Result.Loading)
        try {
            medamiDao.insertAccount(account.toUserAccountDB())
            emit(Result.Success(account))
        }catch (e: Exception){
            emit(Result.Error(e.message!!))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateAccount(account: UserAccount): Flow<Result<Boolean>> = flow{
        emit(Result.Loading)
        try{
            medamiDao.updateAccount(account.toUserAccountDB())
            emit(Result.Success(true))
        }catch (e: Exception){
            emit(Result.Error(e.message!!))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun logOut(account: UserAccount): Flow<Result<Boolean>> = flow{
        /***
         * Làm gì đó khi người dùng đăng xuất.
         * ***/
    }

    override suspend fun addUser(user: User): Flow<Result<Boolean>> = flow{
        emit(Result.Loading)
        try{
            medamiDao.insertUser(user.toUserDB())
            emit(Result.Success(true))
        }catch (e: Exception){
            emit(Result.Error(e.message!!))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateUser(user: User): Flow<Result<Boolean>> = flow{
        emit(Result.Loading)
        try{
            medamiDao.updateUser(user.toUserDB())
            emit(Result.Success(true))
        }catch (e: Exception){
            emit(Result.Error(e.message!!))
        }
    }

    override suspend fun getPrescriptionsOfUser(user: User): Flow<Result<List<Prescription>>> = flow{
        emit(Result.Loading)
        try {
            val listPrescription = medamiDao.getPrescriptionsOfUser(user.id)
            emitAll(
                listPrescription.map { list ->
                    Result.Success(list.map { it.toPrescription() })
                }
            )
        }catch (e: Exception){
            emit(Result.Error(e.message!!))
        }
    }.flowOn(Dispatchers.IO)

}