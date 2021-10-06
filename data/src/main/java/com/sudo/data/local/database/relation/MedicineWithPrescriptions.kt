package com.sudo.data.local.database.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.sudo.data.local.database.models.MedicineDB
import com.sudo.data.local.database.models.PrescriptionDB
import com.sudo.data.local.database.models.PrescriptionMedicineCrossPref

data class MedicineWithPrescriptions(
    @Embedded val medicineDB: MedicineDB,
    @Relation(
        parentColumn = "medicine_id",
        entityColumn = "prescription_id",
        associateBy = Junction(PrescriptionMedicineCrossPref::class)
    )
    val listPrescriptionDB: List<PrescriptionDB>
)
