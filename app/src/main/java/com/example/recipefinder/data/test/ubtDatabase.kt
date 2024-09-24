package com.example.recipefinder.data.test

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipefinder.data.mbrojtja.*

@Database(entities = [team:: class,player::class], version = 3)
abstract class ubtDatabase : RoomDatabase() {

    abstract fun playerDao() : PlayerDao

    companion object{

        @Volatile
        private var INSTANCE : ubtDatabase? = null

        fun getDatabase(context: Context): ubtDatabase{

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ubtDatabase::class.java,
                    "ubt_Database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()


                //

                INSTANCE = instance
                return instance
            }

        }

    }

}
