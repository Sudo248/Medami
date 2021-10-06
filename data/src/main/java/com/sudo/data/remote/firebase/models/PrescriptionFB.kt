package com.sudo.data.remote.firebase.models

import com.google.firebase.Timestamp

data class PrescriptionFB(
    var prescriptionId: String,
    val userId: String,
    val diagnose: String,
    val date: Timestamp,
    val isUse: Boolean,
    val type: String,
    val nameOfDoctor: String?,
    val adviceOfDoctor: String?
)