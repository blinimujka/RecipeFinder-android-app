package com.example.recipefinder.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipefinder.domain.models.CartItem
import com.example.recipefinder.domain.repository.CartDao

@Database(entities = [CartItem :: class], version = 1)
abstract class AppDatabase : RoomDatabase() {

        abstract fun cartDao() : CartDao

        companion object{

                @Volatile
                private var INSTANCE : AppDatabase? = null

                fun getDatabase(context: Context): AppDatabase{

                        val tempInstance = INSTANCE
                        if(tempInstance != null){
                                return tempInstance
                        }
                        synchronized(this){
                                val instance = Room.databaseBuilder(
                                        context.applicationContext,
                                        AppDatabase::class.java,
                                        "app_database"
                                ).allowMainThreadQueries().build()
                                //

                                INSTANCE = instance
                                return instance
                        }

                }

        }

}
