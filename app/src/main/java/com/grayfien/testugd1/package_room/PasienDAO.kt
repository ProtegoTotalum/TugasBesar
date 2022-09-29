package com.grayfien.testugd1.package_room

import androidx.room.*


@Dao
interface PasienDAO {
    @Insert
    suspend fun addPasien(pasien: Pasien)

    @Update
    suspend fun updatePasien(pasien: Pasien)

    @Delete
    suspend fun deletePasien(pasien: Pasien)

    @Query("SELECT * FROM pasien")
    suspend fun getPasiens(): List<Pasien>

    @Query("SELECT * FROM pasien WHERE id =:pasien_id")
    suspend fun getPasien(pasien_id: Int) : List<Pasien>

    @Query("SELECT *FROM pasien where id =:pasien_id")
    suspend fun getPasienID(pasien_id: Int?) : Pasien
}