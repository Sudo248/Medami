package com.sudo.domain.entities

data class Prescription(
    val id: String,
    // người sở hữu đơn thuốc.
    val userId: String,
    // chuẩn đoán.
    val diagnose: String,
    // ngày lấy thuốc.
    val date: Long,
    // đang uống đơn thuốc này.
    val isUse: Boolean,
    // loại đơn thuốc: tự mua or do bác sĩ kê đơn.
    val type: String,
    // Tên bác sĩ kê đơn nếu có.
    val nameDoctor: String?,
    // lời khuyên của bác sĩ nếu có.
    val adviceOfDoctor: String?,
    // danh sách thuốc trong đơn.
    val listMedicine: List<Medicine>? = null
)