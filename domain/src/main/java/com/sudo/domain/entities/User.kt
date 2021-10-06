package com.sudo.domain.entities

data class User(
    val id: String,
    // tên người dùng.
    val firstName: String,
    val lastName: String,
    // số điện thoại.
    val phoneNumber: String,
    // ngày sinh
    val dateOfBirth: Long,
    // cân nặng
    val weight: Short,
    // chiều cao.
    val height: Short,
    // danh sách đơn thuốc.
    val listPrescription: List<Prescription>? = null
)