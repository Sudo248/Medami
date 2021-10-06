package com.sudo.data.local.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_table")
data class UserAccountDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "email_phone")
    val emailOrPhone: String,
    val password: String,
    @ColumnInfo(name = "user_id")
    val userId: String
)