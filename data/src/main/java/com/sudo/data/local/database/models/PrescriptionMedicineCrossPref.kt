package com.sudo.data.local.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "prescription_medicine_cross_pref",
    primaryKeys = ["prescription_id", "medicine_id"]
)
data class PrescriptionMedicineCrossPref(
    @ColumnInfo(name = "prescription_id")
    val prescriptionId: String,
    @ColumnInfo(name = "medicine_id")
    val medicineId: String
)