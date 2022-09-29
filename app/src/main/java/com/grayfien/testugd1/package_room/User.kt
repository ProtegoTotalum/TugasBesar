package com.grayfien.testugd1.package_room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val username: String,
    val password: String,
    val email: String,
    val tglLahir: String,
    val noTelp: String
)
