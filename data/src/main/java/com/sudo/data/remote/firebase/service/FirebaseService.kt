package com.sudo.data.remote.firebase.service

import com.google.firebase.auth.FirebaseAuth
import com.sudo.data.remote.firebase.models.UserAccountFB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FirebaseService{
    suspend fun signIn(account: UserAccountFB): UserAccountFB?
    suspend fun register(account: UserAccountFB): UserAccountFB?
    suspend fun signOut()

    suspend fun getUserByAccountId(id: String): Flow<>
    suspend fun getPrescriptionsById(vararg listPrescriptionId: String)
    suspend fun getMedicinesById(vararg listMedicineId: String)
    suspend fun getNotificationById(vararg listNotificationsId: String)

}