package com.sudo.data.local.database.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.sudo.data.local.database.models.MedicineDB
import com.sudo.data.local.database.models.PrescriptionDB
import com.sudo.data.local.database.models.PrescriptionMedicineCrossPref

data class PrescriptionWithMedicines(
    @Embedded val prescriptionDB: PrescriptionDB,
    @Relation(
        parentColumn = "prescription_id",
        entityColumn = "medicine_id",
        associateBy = Junction(PrescriptionMedicineCrossPref::class)
    )
    val listMedicineDB: List<MedicineDB>
)