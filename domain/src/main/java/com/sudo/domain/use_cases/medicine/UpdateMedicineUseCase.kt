package com.sudo.domain.use_cases.medicine

import com.sudo.domain.entities.Medicine
import com.sudo.domain.repositories.medicine.MedicineRepository

class UpdateMedicineUseCase(private val repository: MedicineRepository) {
    suspend operator fun invoke(medicines: Medicine) =
        repository.updateMedicine(medicines)
}