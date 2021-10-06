package com.sudo.domain.use_cases.prescription

import com.sudo.domain.entities.Prescription
import com.sudo.domain.repositories.medicine.MedicineRepository

class DeletePrescriptionUseCase(private val repository: MedicineRepository) {
    suspend operator fun invoke(vararg prescription: Prescription) =
        repository.deletePrescription(*prescription)
}