package com.dibeqiraj.cakeapp.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dibeqiraj.cakeapp.database.dao.CakeDao
import com.dibeqiraj.cakeapp.database.entity.Cake

@Database(entities = [(Cake::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun cakeDao(): CakeDao

}