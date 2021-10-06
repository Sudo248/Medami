package com.sudo.domain.use_cases.relation

import com.sudo.domain.entities.Prescription
import com.sudo.domain.repositories.medicine.MedicineRepository

class GetPrescriptionWithMedicinesUseCase(private val repository: MedicineRepository) {
    suspend operator fun invoke(prescription: Prescription) =
        repository.getPrescriptionWithMedicines(prescription)
}