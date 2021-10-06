package com.sudo.domain.use_cases.prescription

import com.sudo.domain.repositories.medicine.MedicineRepository

class GetPrescriptionByDateUseCase(private val repository: MedicineRepository) {
    suspend operator fun invoke(date: Long) =
        repository.getPrescriptionByDate(date)
}