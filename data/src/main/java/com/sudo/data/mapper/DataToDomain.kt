package com.sudo.data.mapper

import com.sudo.data.local.database.models.*
import com.sudo.data.remote.firebase.models.*
import com.sudo.domain.entities.*


internal fun UserDB.toUser() = User(
    id = this.userId,
    firstName = this.firstName,
    lastName = this.lastName,
    phoneNumber = this.phoneNumber,
    dateOfBirth = this.dateOfBirth,
    weight = this.weight,
    height = this.height,
)

internal fun PrescriptionDB.toPrescription() = Prescription(
    id = this.prescriptionId,
    userId = this.userId,
    diagnose = this.diagnose,
    date = this.date,
    isUse = this.isUse,
    type = this.type,
    nameDoctor = this.nameOfDoctor,
    adviceOfDoctor = this.adviceOfDoctor
)

internal fun MedicineDB.toMedicine() = Medicine(
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

internal fun NotificationDB.toNotification() = Notification(
    id = this.notificationId,
    medicineId = this.medicineId,
    date = this.date,
    note = this.note
)

internal fun UserAccountDB.toUserAccount() = UserAccount(
    emailOrPhone = this.emailOrPhone,
    password = this.password,
    userId = this.userId
)

internal fun UserFB.toUser() = User(
    id = this.userId,
    firstName = this.firstName,
    lastName = this.lastName,
    phoneNumber = this.phoneNumber,
    dateOfBirth = this.dateOfBirth.toDate().time,
    weight = this.weight,
    height = this.height,
)

internal fun PrescriptionFB.toPrescription() = Prescription(
    id = this.prescriptionId,
    userId = this.userId,
    diagnose = this.diagnose,
    date = this.date.toDate().time,
    isUse = this.isUse,
    type = this.type,
    nameDoctor = this.nameOfDoctor,
    adviceOfDoctor = this.adviceOfDoctor
)

internal fun MedicineFB.toMedicine() = Medicine(
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

internal fun NotificationFB.toNotification() = Notification(
    id = this.notificationId,
    medicineId = this.medicineId,
    date = this.date.toDate().time,
    note = this.note
)

internal fun UserAccountFB.toUserAccount() = UserAccount(
    emailOrPhone = this.emailOrPhone,
    password = this.password,
    userId = this.userId
)