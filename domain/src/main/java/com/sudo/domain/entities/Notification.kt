package com.sudo.domain.entities

data class Notification (
    val id: String,
    val medicineId: String,
    // thời gian thông báo
    val date: Long,
    // Chú thích cho thông báo.
    val note: String?
)