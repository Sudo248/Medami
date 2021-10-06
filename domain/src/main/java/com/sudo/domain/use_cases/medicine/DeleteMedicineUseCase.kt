package com.sudo.domain.use_cases.medicine

import com.sudo.domain.entities.Medicine
import com.sudo.domain.repositories.medicine.MedicineRepository

class DeleteMedicineUseCase(private val repository: MedicineRepository) {
    suspend operator fun invoke(vararg medicines: Medicine) =
        repository.deleteMedicine(*medicines)
}