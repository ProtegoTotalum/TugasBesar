package com.grayfien.testugd1.package_room

import androidx.room.*

@Dao
interface ObatDAO {
    @Insert
    suspend fun addObat(obat: Obat)

    @Update
    suspend fun updateObat(obat: Obat)

    @Delete
    suspend fun deleteObat(obat: Obat)

    @Query("SELECT *FROM obat")
    suspend fun getObat(): List<Obat>

    @Query("SELECT * FROM obat where id =:obat_id")
    suspend fun getObat(obat_id: Int): List<Obat>
}