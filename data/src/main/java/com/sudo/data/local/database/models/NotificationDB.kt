package com.sudo.data.local.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification_table")
data class NotificationDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "notification_id")
    var notificationId: String,
    @ColumnInfo(name = "medicine_id")
    val medicineId: String,
    val date: Long,
    val note: String?
)