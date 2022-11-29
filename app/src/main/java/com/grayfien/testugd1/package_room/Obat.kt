package com.grayfien.testugd1.package_room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Obat(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val namaObat: String,
    val jenisObat: String,
    val deskripsi: String
)

