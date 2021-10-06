package com.sudo.data.remote.firebase.models

import com.google.firebase.Timestamp

data class NotificationFB(
    var notificationId: String,
    val medicineId: String,
    val date: Timestamp,
    val note: String?
)
