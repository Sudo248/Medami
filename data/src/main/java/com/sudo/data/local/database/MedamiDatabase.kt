package com.sudo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sudo.data.local.database.dao.MedamiDao
import com.sudo.data.local.database.models.*

@Database(
    entities = [
        UserDB::class,
        PrescriptionDB::class,
        MedicineDB::class,
        NotificationDB::class,
        PrescriptionMedicineCrossPref::class,
        UserAccountDB::class
   ],
    version = 1,
    exportSchema = false
)
abstract class MedamiDatabase : RoomDatabase() {
    abstract val medamiDao: MedamiDao
}