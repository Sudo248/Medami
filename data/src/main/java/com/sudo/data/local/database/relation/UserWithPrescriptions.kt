package com.sudo.data.local.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.sudo.data.local.database.models.PrescriptionDB
import com.sudo.data.local.database.models.UserDB

data class UserWithPrescriptions(
    @Embedded val userDB: UserDB,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id"
    )
    val listPrescriptionDB: List<PrescriptionDB>
)