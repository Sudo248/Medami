package com.sudo.data.repositories.medicine

import com.sudo.data.local.database.dao.MedamiDao
import com.sudo.data.local.database.models.PrescriptionMedicineCrossPref
import com.sudo.data.mapper.*
import com.sudo.domain.entities.Medicine
import com.sudo.domain.entities.Notification
import com.sudo.domain.entities.Prescription
import com.sudo.domain.repositories.medicine.MedicineRepository
import com.sudo.domain.common.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MedicineRepositoryImplDB @Inject constructor(
    private val medamiDao: MedamiDao
) : MedicineRepository {

            /******************  Prescription  ************************/

    override suspend fun getAllPrescription(): Flow<Result<List<Prescription>>> = flow{
        TODO("Get all prescription which user has had")
    }

    override suspend fun getCurrentPrescription(): Flow<Result<List<Prescription>>> =flow{
        emit(Result.Loading)
        val listPrescriptionDB = medamiDao.getCurrentPrescription()
        emitAll(
            listPrescriptionDB.map { list ->
                Result.Success(list.map { it.toPrescription() })
            }
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun getPrescriptionByDate(date: Long): Flow<Result<List<Prescription>>> = flow{
        emit(Result.Loading)
        val listPrescriptionDB = medamiDao.getPrescriptionByDate(date)
        emitAll(
            listPrescriptionDB.map { list ->
                Result.Success(list.map { it.toPrescription() })
            }
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun addPrescription(prescription: Prescription) {
        withContext(Dispatchers.IO){
            medamiDao.insertPrescription(prescription.toPrescriptionDB())
            val prescriptionId = medamiDao.getLastPrescriptionId()
            prescription.listMedicine?.let { list ->
                list.forEach { medicine ->
                    addPrescriptionMedicineCrossPref(PrescriptionMedicineCrossPref(prescriptionId, medicine.id))
                }
            }
        }
    }

    override suspend fun deletePrescription(vararg prescriptions: Prescription) {
        withContext(Dispatchers.IO){
            val prescriptionsDB = prescriptions.map { it.toPrescriptionDB() }.toTypedArray()
            medamiDao.deletePrescription(*prescriptionsDB)
            for( prescription in prescriptions){
                prescription.listMedicine?.let { list ->
                    list.forEach { medicine ->
                        deleteMedicine(medicine)
                        deletePrescriptionMedicineCrossPref(PrescriptionMedicineCrossPref(prescription.id, medicine.id))
                    }
                }
            }
        }
    }

    override suspend fun updatePrescription(prescription: Prescription) {
        withContext(Dispatchers.IO){
            medamiDao.updatePrescription(prescription.toPrescriptionDB())
        }
    }

    /******************  Medicine  ************************/

    override suspend fun getAllMedicine(): Flow<Result<List<Medicine>>> {
        TODO("Get all medicine which user has taken")
    }

    override suspend fun getMedicineByDate(date: Long): Flow<Result<List<Medicine>>> = flow{
        emit(Result.Loading)
        val medicinesDB = medamiDao.getMedicineByDate(date)
        emitAll(
            medicinesDB.map { list ->
                Result.Success(list.map { it.toMedicine() })
            }
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun addMedicine(medicine: Medicine) {
        withContext(Dispatchers.IO) {
            medamiDao.insertMedicine(medicine.toMedicineDB())
        }
    }

    override suspend fun deleteMedicine(vararg medicines: Medicine) {
        withContext(Dispatchers.IO){
            val medicinesDB = medicines.map { it.toMedicineDB() }.toTypedArray()
            medamiDao.deleteMedicine(*medicinesDB)
            for(medicine in medicines){
                medicine.notifications?.let { list ->
                    list.forEach{
                        deleteNotifications(it)
                    }
                }
            }
        }
    }

    override suspend fun updateMedicine(medicine: Medicine) {
        withContext(Dispatchers.IO){
            medamiDao.updateMedicine(medicine.toMedicineDB())
        }
    }

    /******************  Notification  ************************/

    override suspend fun getNotificationsOfMedicine(medicine: Medicine): Flow<Result<List<Notification>>> = flow{
        emit(Result.Loading)
        val listNotificationsDB = medamiDao.getNotificationsOfMedicine(medicine.id)
        emitAll(
            listNotificationsDB.map { list ->
                Result.Success(list.map { it.toNotification() })
            }
        )
    }

    override suspend fun getNotificationsByDate(date: Long): Flow<Result<List<Notification>>> =flow{
        emit(Result.Loading)
        val listNotificationsDB = medamiDao.getNotificationsByDate(date)
        emitAll(
            listNotificationsDB.map { list ->
                Result.Success(list.map { it.toNotification() })
            }
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun updateNotification(notification: Notification) {
        withContext(Dispatchers.IO){
            medamiDao.updateNotification(notification.toNotificationDB())
        }
    }

    override suspend fun deleteNotifications(vararg notifications: Notification) {
        withContext(Dispatchers.IO){
            val listNotificationsDB = notifications.map { it.toNotificationDB() }.toTypedArray()
            medamiDao.deleteNotification(*listNotificationsDB)
        }
    }

    /******************  Relationship  ************************/

    override suspend fun getPrescriptionWithMedicines(prescription: Prescription): Flow<Result<Prescription>> = flow{
        emit(Result.Loading)
        val prescriptionWithMedicines = medamiDao.getMedicinesByPrescriptionId(prescription.id)
        val listMedicines = prescriptionWithMedicines.listMedicineDB.map { it.toMedicine() }
        val newPrescription = prescription.copy(listMedicine = listMedicines)
        emit(Result.Success(newPrescription))
    }.flowOn(Dispatchers.IO)

    override suspend fun getMedicineWithPrescriptions(medicine: Medicine): Flow<Result<Medicine>> = flow{
        emit(Result.Loading)
        val medicineWithPrescriptions = medamiDao.getPrescriptionsByMedicineId(medicine.id)
        val listPrescription = medicineWithPrescriptions.listPrescriptionDB.map { it.toPrescription() }
        val newMedicines = medicine.copy(listPrescription = listPrescription)
        emit(Result.Success(newMedicines))
    }.flowOn(Dispatchers.IO)

    override suspend fun getMedicineWithNotifies(medicine: Medicine): Flow<Result<Medicine>> = flow{
        emit(Result.Loading)
        val medicineWithNotification = medamiDao.getNotificationsByMedicineId(medicine.id)
        val listNotification = medicineWithNotification.listNotificationDB.map { it.toNotification() }
        val newMedicines = medicine.copy(notifications = listNotification)
        emit(Result.Success(newMedicines))
    }.flowOn(Dispatchers.IO)

    private suspend fun addPrescriptionMedicineCrossPref(prescriptionMedicineCrossPref: PrescriptionMedicineCrossPref){
        withContext(Dispatchers.IO){
            medamiDao.insertPrescriptionMedicineCrossPref(prescriptionMedicineCrossPref)
        }
    }

    private suspend fun deletePrescriptionMedicineCrossPref(prescriptionMedicineCrossPref: PrescriptionMedicineCrossPref){
        withContext(Dispatchers.IO){
            medamiDao.deletePrescriptionMedicineCrossPref(prescriptionMedicineCrossPref)
        }
    }

    suspend fun updatePrescriptionMedicineCrossPref(prescriptionMedicineCrossPref: PrescriptionMedicineCrossPref){
        withContext(Dispatchers.IO){
            medamiDao.updatePrescriptionMedicineCrossPref(prescriptionMedicineCrossPref)
        }
    }
}