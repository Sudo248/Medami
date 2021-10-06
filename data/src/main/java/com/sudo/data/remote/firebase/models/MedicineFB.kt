package com.sudo.data.remote.firebase.models

import com.google.firebase.Timestamp

data class MedicineFB(
    var medicineId: String,
    val name: String,
    val frequency: Byte,
    val times: Byte,
    val amount: Byte,
    val sum: Short,
    val unit: String,
    val fromDate: Timestamp,
    val toDate: Timestamp
)
