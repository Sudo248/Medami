package com.sudo.data.local.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.sudo.data.local.database.models.MedicineDB
import com.sudo.data.local.database.models.NotificationDB

data class MedicineWithNotifications(
    @Embedded val medicineDB: MedicineDB,
    @Relation(
        parentColumn = "medicine_id",
        entityColumn = "medicine_id"
    )
    val listNotificationDB: List<NotificationDB>
)
