package com.grayfien.testugd1.package_room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

class ObatDB {
    @Database(
        entities = [Pasien::class],
        version = 1
    )

    abstract class ObatDB : RoomDatabase() {

        abstract fun obatDao(): ObatDAO

        companion object {
            @Volatile
            private var instance: ObatDB? = null
            private val LOCK = Any()

            operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

            private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                    context.applicationContext,
                    ObatDB::class.java,
                    "Obat.db"
                ).build()
        }
    }
}