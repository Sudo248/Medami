package com.sudo.domain.use_cases.medicine

import com.sudo.domain.repositories.medicine.MedicineRepository

class GetMedicineByDateUseCase(private val repository: MedicineRepository) {
    suspend operator fun invoke(date: Long) =
        repository.getMedicineByDate(date)
}