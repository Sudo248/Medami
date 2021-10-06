package com.sudo.data.local.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_id")
    var userId: String,
    val firstName: String,
    val lastName: String,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    @ColumnInfo(name = "date_of_birth")
    val dateOfBirth: Long,
    val weight: Short,
    val height: Short
)