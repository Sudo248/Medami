package com.sudo.domain.use_cases.relation

import com.sudo.domain.entities.Medicine
import com.sudo.domain.repositories.medicine.MedicineRepository

class GetMedicineWithPrescriptionsUseCase(private val repository: MedicineRepository) {
    suspend operator fun invoke(medicine: Medicine) =
        repository.getMedicineWithPrescriptions(medicine)
}