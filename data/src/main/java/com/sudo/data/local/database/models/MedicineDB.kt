package com.sudo.data.local.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine_table")
data class MedicineDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "medicine_id")
    var medicineId: String,
    val name: String,
    val frequency: Byte,
    val times: Byte,
    val amount: Byte,
    val sum: Short,
    val unit: String,
    @ColumnInfo(name = "date_start")
    val fromDate: Long,
    @ColumnInfo(name = "date_end")
    val toDate: Long
)