package com.sudo.data.mapper

import com.google.firebase.Timestamp
import com.sudo.data.local.database.models.*
import com.sudo.data.remote.firebase.models.*
import com.sudo.domain.entities.*
import java.util.*

internal fun User.toUserFB() = UserFB(
    userId = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    phoneNumber = this.phoneNumber,
    dateOfBirth = Timestamp(Date(this.dateOfBirth)),
    weight = this.weight,
    height = this.weight
)



internal fun Prescription.toPrescriptionFB() = PrescriptionFB(
    prescriptionId = this.id,
    userId = this.userId,
    diagnose = this.diagnose,
    date = Timestamp(Date(this.date)),
    isUse = this.isUse,
    type = this.type,
    nameOfDoctor = this.nameDoctor,
    adviceOfDoctor = this.adviceOfDoctor
)

internal fun Medicine.toMedicineFB() = MedicineFB(
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

internal fun Notification.toNotificationFB() = NotificationFB(
    notificationId = this.id,
    medicineId = this.medicineId,
    date = Timestamp(Date(this.date)),
    note = this.note
)

internal fun UserAccount.toUserAccountFB() = UserAccountFB(
    emailOrPhone = this.emailOrPhone,
    password = this.password,
    userId = this.userId
)

internal fun User.toUserDB() = UserDB(
    userId = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    phoneNumber = this.phoneNumber,
    dateOfBirth = this.dateOfBirth,
    weight = this.weight,
    height = this.weight
)


internal fun Prescription.toPrescriptionDB() = PrescriptionDB(
    prescriptionId = this.id,
    userId = this.userId,
    diagnose = this.diagnose,
    date = this.date,
    isUse = this.isUse,
    type = this.type,
    nameOfDoctor = this.nameDoctor,
    adviceOfDoctor = this.adviceOfDoctor
)


internal fun Medicine.toMedicineDB() = MedicineDB(
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

internal fun Notification.toNotificationDB() = NotificationDB(
    notificationId = this.id,
    medicineId = this.medicineId,
    date = this.date,
    note = this.note
)

internal fun UserAccount.toUserAccountDB() = UserAccountDB(
    emailOrPhone = this.emailOrPhone,
    password = this.password,
    userId = this.userId
)