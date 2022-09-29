package com.grayfien.testugd1.package_room

import androidx.room.*


@Dao
interface UserDAO {
    @Insert
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT *FROM user")
    suspend fun getAllUser() : List<User>


    @Query("SELECT  *FROM user WHERE username=:user AND password=:pass")
    suspend fun getUser(user: String, pass:String) : User

    @Query("SELECT *FROM user where id =:user_id")
    suspend fun getUserID(user_id: Int?) : User
}