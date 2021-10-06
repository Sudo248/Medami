package com.sudo.data.local.database.dao

import androidx.room.*
import com.sudo.data.local.database.models.*
import com.sudo.data.local.database.relation.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MedamiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: UserAccountDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrescription(prescription: PrescriptionDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicine(medicine: MedicineDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: NotificationDB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPrescriptionMedicineCrossPref(prescriptionMedicine: PrescriptionMedicineCrossPref)

    /*****************************************************************************/

    @Update
    suspend fun updateAccount(account: UserAccountDB)

    @Update
    suspend fun updateUser(user: UserDB)

    @Update
    suspend fun updatePrescription(prescription: PrescriptionDB)

    @Update
    suspend fun updateMedicine(medicine: MedicineDB)

    @Update
    suspend fun updateNotification(notification: NotificationDB)

    @Update
    suspend fun updatePrescriptionMedicineCrossPref(prescriptionMedicine: PrescriptionMedicineCrossPref)

    /*****************************************************************************/

    @Delete
    suspend fun deletePrescription(vararg prescription: PrescriptionDB)

    @Delete
    suspend fun deleteMedicine(vararg medicine: MedicineDB)

    @Delete
    suspend fun deleteNotification(vararg notification: NotificationDB)

    @Delete
    suspend fun deletePrescriptionMedicineCrossPref(prescriptionMedicine: PrescriptionMedicineCrossPref)

    /*****************************************************************************/

    // Account
    @Query("SELECT * FROM account_table WHERE email_phone = :emailPhone LIMIT 1")
    suspend fun getAccount(emailPhone: String): UserAccountDB

    // User
    @Query("SELECT * FROM user_table WHERE user_id = :id LIMIT 1")
    suspend fun getUserById(id: String): UserDB

    @Transaction
    @Query("SELECT * FROM user_table")
    fun getUserWithPrescriptions(): Flow<List<UserWithPrescriptions>>

    // prescription
    @Query("SELECT * FROM prescription_table WHERE user_id = :id")
    fun getPrescriptionsOfUser(id: String): Flow<List<PrescriptionDB>>

    @Query("SELECT * FROM prescription_table WHERE date = :date")
    fun getPrescriptionByDate(date: Long): Flow<List<PrescriptionDB>>

    @Query("SELECT * FROM prescription_table WHERE isUse")
    fun getCurrentPrescription(): Flow<List<PrescriptionDB>>

    @Query("SELECT MAX(prescription_id) FROM prescription_table")
    suspend fun getLastPrescriptionId(): String

    //Medicine
    @Query("SELECT * FROM medicine_table WHERE date_start <= :date AND date_end >= :date")
    fun getMedicineByDate(date: Long): Flow<List<MedicineDB>>

    // Notification
    @Query("SELECT * FROM notification_table WHERE medicine_id = :id")
    fun getNotificationsOfMedicine(id: String): Flow<List<NotificationDB>>

    @Query("SELECT * FROM notification_table WHERE date = :date")
    fun getNotificationsByDate(date: Long): Flow<List<NotificationDB>>

    @Query("SELECT MAX(medicine_id) FROM medicine_table")
    suspend fun getLastMedicineId(): String

    // relation
    @Transaction
    @Query("SELECT * FROM prescription_table WHERE prescription_id = :id")
    fun getMedicinesByPrescriptionId(id: String): PrescriptionWithMedicines

    @Transaction
    @Query("SELECT * FROM prescription_table WHERE user_id = :id")
    fun getPrescriptionsWithMedicineByUserId(id: String): Flow<List<PrescriptionWithMedicines>>

    @Transaction
    @Query("SELECT * FROM medicine_table WHERE medicine_id = :id")
    fun getPrescriptionsByMedicineId(id: String): MedicineWithPrescriptions

    @Transaction
    @Query("SELECT * FROM medicine_table WHERE medicine_id = :id")
    fun getNotificationsByMedicineId(id: String): MedicineWithNotifications

}