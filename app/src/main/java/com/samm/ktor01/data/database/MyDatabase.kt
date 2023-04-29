package com.samm.ktor01.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.samm.ktor01.domain.models.Apod

@Database(entities = [Apod::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun myDao(): FavoritesDao

    companion object {

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MyDatabase::class.java, "my_database"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}