package com.sudo.domain.use_cases.medicine

import com.sudo.domain.repositories.medicine.MedicineRepository

class GetAllMedicineUseCase(private val repository: MedicineRepository) {
    suspend operator fun invoke() =
        repository.getAllMedicine()
}