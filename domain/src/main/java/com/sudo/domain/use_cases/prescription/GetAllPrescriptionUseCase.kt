package com.sudo.domain.use_cases.prescription

import com.sudo.domain.repositories.medicine.MedicineRepository

class GetAllPrescriptionUseCase(private val repository: MedicineRepository) {
    suspend operator fun invoke() = repository.getAllPrescription()
}