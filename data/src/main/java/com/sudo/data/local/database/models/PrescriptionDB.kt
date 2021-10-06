package com.sudo.data.local.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prescription_table")
data class PrescriptionDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "prescription_id")
    var prescriptionId: String,
    @ColumnInfo(name = "user_id")
    val userId: String,
    val diagnose: String,
    val date: Long,
    val isUse: Boolean,
    val type: String,
    @ColumnInfo(name = "name_of_doctor")
    val nameOfDoctor: String?,
    @ColumnInfo(name = "advice_of_doctor")
    val adviceOfDoctor: String?
)