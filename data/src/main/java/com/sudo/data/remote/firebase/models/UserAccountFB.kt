package com.sudo.data.remote.firebase.models

data class UserAccountFB(
    val emailOrPhone: String,
    val password: String,
    val userId: String
)