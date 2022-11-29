package com.grayfien.testugd1.package_room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [User::class],
    version = 1
)

abstract class UserDB : RoomDatabase() {

    abstract fun userDao(): UserDAO

    companion object {
        @Volatile
        private var instance: UserDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDB::class.java,
                "User.db"
            ).build()

        fun getDatabase(context: Context): UserDB {
            val temp = instance
            if (temp != null) {
                return temp
            }

            synchronized(this) {
                val instance1 = Room.databaseBuilder(
                    context.applicationContext,
                    UserDB::class.java,
                    "users_database"
                ).build()
                instance = instance1
                return instance1
            }
        }
    }
}