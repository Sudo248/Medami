package com.sudo.data.remote.firebase.service

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.sudo.data.remote.firebase.models.*
import com.sudo.domain.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) : FirebaseService {
    companion object val TAG = "FirebaseServiceImpl"
    override suspend fun checkAccount(): String? {
        Log.d(TAG, "checkAccount")
        val currentAccount = auth.currentUser ?: return null
        return currentAccount.uid
    }

    override suspend fun signIn(account: UserAccountFB): Flow<Result<String>> = flow{
        Log.d(TAG, "signIn")
        try{
            auth.signInWithEmailAndPassword(
                phoneNumberToEmailOrNot(account.emailOrPhone),
                account.password
            ).await()
            val account = auth.currentUser
            if(account == null){
                emit(Result.Error("Tài khoản không khả dụng hoặc sai mật khẩu."))
                Log.d(TAG, "signIn: $account")
            }else{
                emit(Result.Success(account.uid))
                Log.d(TAG, "signIn: ${account.uid}")
            }
        }catch (e: Exception){
            emit(Result.Error(e.message!!))
            Log.d(TAG, "signIn: ${e.message}")
        }
    }

    override suspend fun register(account: UserAccountFB): Flow<Result<UserAccountFB>> = flow{
        Log.d(TAG, "register")
        try{
            auth.createUserWithEmailAndPassword(
                phoneNumberToEmailOrNot(account.emailOrPhone),
                account.password
            )
            val currentAccount = auth.currentUser
            if(currentAccount == null){
                emit(Result.Error("Tài khoản không khả dụng hoặc sai mật khẩu."))
                Log.d(TAG, "register: $currentAccount")
            }else{
                val accountFB = UserAccountFB(account.emailOrPhone, account.password, currentAccount.uid)
                emit(Result.Success(accountFB))
                Log.d(TAG, "register: $accountFB")
            }
        }catch (e: Exception){
            emit(Result.Error(e.message!!))
            Log.d(TAG, "register: ${e.message}")
        }
    }

    override suspend fun signOut() {
        Log.d(TAG, "signOut")
        try {
            auth.signOut()
        }catch (e: Exception){
            Log.d(TAG, "signOut: ${e.message}")
        }
    }

    override suspend fun getUserFBByAccountId(id: String): Flow<Result<UserFB>> = flow{
        Log.d(TAG, "getUserFBByAccountId")
        try{
            val querySnapshot = fireStore.collection("users")
                .whereEqualTo("__name__",id)
                .get()
                .await()
            val userFB: UserFB? = querySnapshot.documents[0].toObject()
            userFB?.let {
                emit(Result.Success(it))
            }
        }catch (e: Exception){
            emit(Result.Error("${e.message}"))
        }
    }

    override suspend fun getPrescriptionsFBById(vararg listPrescriptionId: String): Flow<Result<PrescriptionFB>> = flow{
        try {
            val querySnapshot = fireStore.collection("prescriptions")
            for (id in listPrescriptionId){
                val document = querySnapshot.whereEqualTo("__name__", id)
                            .get()
                            .await()
                            .documents
                val prescriptionFB: PrescriptionFB? = document[0].toObject()
                prescriptionFB?.let {
                    emit(Result.Success(it))
                }
            }
        }catch (e: Exception){
            emit(Result.Error("${e.message}"))
        }
    }

    override suspend fun getMedicinesFBById(vararg listMedicineId: String): Flow<Result<List<MedicineFB>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getNotificationsFBById(vararg listNotificationsId: String): Flow<Result<List<NotificationFB>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getJunctionFBByPrescriptionId(vararg listPrescriptionId: String): Flow<Result<List<JunctionPrescriptionMedicineFB>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getJunctionFBByMedicineId(vararg listMedicineId: String): Flow<Result<List<JunctionPrescriptionMedicineFB>>> {
        TODO("Not yet implemented")
    }
    private suspend fun phoneNumberToEmailOrNot(emailPhone: String): String{
        return if(emailPhone.matches(Regex("^[0-9]{10,13}$"))) {
            "$emailPhone@gmail.com"
        }else{
            emailPhone
        }
    }

    private suspend fun emailToPhoneNumberOrNot(emailPhone: String): String{
        return if(emailPhone.matches(Regex("^[0-9]{10,13}@gmail\\.com$"))) {
            emailPhone.split(Regex("@gmail.com"))[0]
        }else{
            emailPhone
        }
    }
}