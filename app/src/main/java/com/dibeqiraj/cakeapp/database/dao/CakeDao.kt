package com.dibeqiraj.cakeapp.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.dibeqiraj.cakeapp.database.entity.Cake

@Dao
interface CakeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cake: Cake)

    @Query("SELECT * FROM cake")
    fun getSavedCakes(): MutableList<Cake>
}