package com.sudo.data.utils

import com.google.firebase.Timestamp
import com.sudo.data.local.database.models.*
import com.sudo.data.remote.firebase.models.*
import com.sudo.domain.entities.*
import java.util.*

/**************************  Convert From domain to Database   **********************************/
fun User.toUserDB() = UserDB(
    userId = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    phoneNumber = this.phoneNumber,
    dateOfBirth = this.dateOfBirth,
    weight = this.weight,
    height = this.weight
)

fun UserDB.toUser() = User(
    id = this.userId,
    firstName = this.firstName,
    lastName = this.lastName,
    phoneNumber = this.phoneNumber,
    dateOfBirth = this.dateOfBirth,
    weight = this.weight,
    height = this.height,
)

fun Prescription.toPrescriptionDB() = PrescriptionDB(
    prescriptionId = this.id,
    userId = this.userId,
    diagnose = this.diagnose,
    date = this.date,
    isUse = this.isUse,
    type = this.type,
    nameOfDoctor = this.nameDoctor,
    adviceOfDoctor = this.adviceOfDoctor
)

fun PrescriptionDB.toPrescription() = Prescription(
    id = this.prescriptionId,
    userId = this.userId,
    diagnose = this.diagnose,
    date = this.date,
    isUse = this.isUse,
    type = this.type,
    nameDoctor = this.nameOfDoctor,
    adviceOfDoctor = this.adviceOfDoctor
)

fun Medicine.toMedicineDB() = MedicineDB(
    medicineId = this.id,
    name = this.name,
    frequency = this.frequency,
    times = this.times,
    amount = this.amount,
    sum = this.remaining,
    unit = this.unit,
    fromDate = this.fromDate,
    toDate = this.toDate
)

fun MedicineDB.toMedicine() = Medicine(
    id = this.medicineId,
    name = this.name,
    frequency = this.frequency,
    times = this.times,
    amount = this.amount,
    remaining = this.sum,
    unit = this.unit,
    fromDate = this.fromDate,
    toDate = this.toDate
)

fun Notification.toNotificationDB() = NotificationDB(
    notificationId = this.id,
    medicineId = this.medicineId,
    date = this.date,
    note = this.note
)

fun NotificationDB.toNotification() = Notification(
    id = this.notificationId,
    medicineId = this.medicineId,
    date = this.date,
    note = this.note
)

fun UserAccount.toUserAccountDB() = UserAccountDB(
    emailOrPhone = this.emailOrPhone,
    password = this.password,
    userId = this.userId
)

fun UserAccountDB.toUserAccount() = UserAccount(
    emailOrPhone = this.emailOrPhone,
    password = this.password,
    userId = this.userId
)

    /**************************  Convert From domain to Firebase   **********************************/

fun User.toUserFB() = UserFB(
        userId = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        phoneNumber = this.phoneNumber,
        dateOfBirth = Timestamp(Date(this.dateOfBirth)),
        weight = this.weight,
        height = this.weight
)

fun UserFB.toUser() = User(
    id = this.userId,
    firstName = this.firstName,
    lastName = this.lastName,
    phoneNumber = this.phoneNumber,
    dateOfBirth = this.dateOfBirth.toDate().time,
    weight = this.weight,
    height = this.height,
)

fun Prescription.toPrescriptionFB() = PrescriptionFB(
    prescriptionId = this.id,
    userId = this.userId,
    diagnose = this.diagnose,
    date = Timestamp(Date(this.date)),
    isUse = this.isUse,
    type = this.type,
    nameOfDoctor = this.nameDoctor,
    adviceOfDoctor = this.adviceOfDoctor
)

fun PrescriptionFB.toPrescription() = Prescription(
    id = this.prescriptionId,
    userId = this.userId,
    diagnose = this.diagnose,
    date = this.date.toDate().time,
    isUse = this.isUse,
    type = this.type,
    nameDoctor = this.nameOfDoctor,
    adviceOfDoctor = this.adviceOfDoctor
)

fun Medicine.toMedicineFB() = MedicineFB(
    medicineId = this.id,
    name = this.name,
    frequency = this.frequency,
    times = this.times,
    amount = this.amount,
    sum = this.remaining,
    unit = this.unit,
    fromDate = Timestamp(Date(this.fromDate)),
    toDate = Timestamp(Date(this.toDate))
)

fun MedicineFB.toMedicine() = Medicine(
    id = this.medicineId,
    name = this.name,
    frequency = this.frequency,
    times = this.times,
    amount = this.amount,
    remaining = this.sum,
    unit = this.unit,
    fromDate = this.fromDate.toDate().time,
    toDate = this.toDate.toDate().time
)

fun Notification.toNotificationFB() = NotificationFB(
    notificationId = this.id,
    medicineId = this.medicineId,
    date = Timestamp(Date(this.date)),
    note = this.note
)

fun NotificationFB.toNotification() = Notification(
    id = this.notificationId,
    medicineId = this.medicineId,
    date = this.date.toDate().time,
    note = this.note
)

fun UserAccount.toUserAccountFB() = UserAccountDB(
    emailOrPhone = this.emailOrPhone,
    password = this.password,
    userId = this.userId
)

fun UserAccountFB.toUserAccount() = UserAccount(
    emailOrPhone = this.emailOrPhone,
    password = this.password,
    userId = this.userId
)


