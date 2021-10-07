package com.sudo.data.remote.firebase.service

import com.sudo.data.remote.firebase.models.*
import com.sudo.domain.common.Result
import kotlinx.coroutines.flow.Flow


interface FirebaseService{
    // kiểm tra xem account còn đăng nhập hay không, nếu con thì trả về userId
    suspend fun checkAccount(): String?
    //đăng nhập nếu thành công trả về userId.
    suspend fun signIn(account: UserAccountFB): Flow<Result<String>>
    // đăng kí 1 tìa khaonr nếu thành công trả về 1 account.
    suspend fun register(account: UserAccountFB): Flow<Result<UserAccountFB>>
    // đăng xuất.
    suspend fun signOut()

    suspend fun getUserFBByAccountId(id: String): Flow<Result<UserFB>>
    suspend fun getPrescriptionsFBById(vararg listPrescriptionId: String): Flow<Result<PrescriptionFB>>
    suspend fun getMedicinesFBById(vararg listMedicineId: String): Flow<Result<List<MedicineFB>>>
    suspend fun getNotificationsFBById(vararg listNotificationsId: String): Flow<Result<List<NotificationFB>>>
    suspend fun getJunctionFBByPrescriptionId(vararg listPrescriptionId: String): Flow<Result<List<JunctionPrescriptionMedicineFB>>>
    suspend fun getJunctionFBByMedicineId(vararg listMedicineId: String): Flow<Result<List<JunctionPrescriptionMedicineFB>>>

}