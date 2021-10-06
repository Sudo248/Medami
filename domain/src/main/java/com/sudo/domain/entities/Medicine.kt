package com.sudo.domain.entities

data class Medicine (
    val id: String,
    // tên thuốc.
    val name: String,
    // tần suất uống hàng tuần.
    val frequency: Byte,
    // uống bao nhiêu lần 1 ngày.
    val times: Byte,
    // số lượng thuốc 1 lần uống.
    val amount: Byte,
    // số lượng thuốc còn lại.
    val remaining: Short,
    // Đơn vị thuốc: viên, ml,...
    val unit: String,
    // Ngày bắt đầu uống thuốc
    val fromDate: Long,
    // Ngày kết thúc uống.
    val toDate: Long,
    // danh sách thông báo.
    val notifications: List<Notification>? = null,
    // danh sách đơn thuốc chứa thuốc này.
    val listPrescription: List<Prescription>? = null
)