package com.adivid.roomdbstevedzak.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.adivid.roomdbstevedzak.model.User


@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update()
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("Delete from user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY ID ASC")
    fun readAllData(): LiveData<List<User>>

}