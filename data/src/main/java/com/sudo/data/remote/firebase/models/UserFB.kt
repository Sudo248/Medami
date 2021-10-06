package com.sudo.data.remote.firebase.models

import com.google.firebase.Timestamp

data class UserFB(
    val userId: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val dateOfBirth: Timestamp,
    val weight: Short,
    val height: Short
)