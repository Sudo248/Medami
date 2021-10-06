package com.sudo.domain.repositories.medicine

import com.sudo.domain.entities.Medicine
import com.sudo.domain.entities.Notification
import com.sudo.domain.entities.Prescription
import com.sudo.domain.common.Result
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {
    suspend fun getAllPrescription(): Flow<Result<List<Prescription>>>
    suspend fun getCurrentPrescription(): Flow<Result<List<Prescription>>>
    suspend fun getPrescriptionByDate(date: Long): Flow<Result<List<Prescription>>>
    suspend fun addPrescription(prescription: Prescription)
    suspend fun deletePrescription(vararg prescriptions: Prescription)
    suspend fun updatePrescription(prescription: Prescription)

    suspend fun getAllMedicine(): Flow<Result<List<Medicine>>>
    suspend fun getMedicineByDate(date: Long): Flow<Result<List<Medicine>>>
    suspend fun addMedicine(medicine: Medicine)
    suspend fun deleteMedicine(vararg medicines: Medicine)
    suspend fun updateMedicine(medicine: Medicine)

    suspend fun getNotificationsOfMedicine(medicine: Medicine): Flow<Result<List<Notification>>>
    suspend fun getNotificationsByDate(date: Long): Flow<Result<List<Notification>>>
    suspend fun updateNotification(notification: Notification)
    suspend fun deleteNotifications(vararg notifications: Notification)

    suspend fun getPrescriptionWithMedicines(prescription: Prescription): Flow<Result<Prescription>>
    suspend fun getMedicineWithPrescriptions(medicine: Medicine): Flow<Result<Medicine>>
    suspend fun getMedicineWithNotifies(medicine: Medicine): Flow<Result<Medicine>>

}