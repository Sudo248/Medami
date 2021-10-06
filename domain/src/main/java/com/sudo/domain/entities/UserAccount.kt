package com.sudo.domain.entities

data class UserAccount(
    val emailOrPhone: String,
    val password: String,
    val userId: String
)
