package com.example.task.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context


@Database(entities = [Task::class], version = 1, exportSchema = false)
public abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDoa


//
//    companion object {
//        @Volatile
//        private var INSTANCE: TaskDatabase? = null
//
//        fun getDatabase(context: Context): TaskDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    TaskDatabase::class.java,
//                    "Task_database"
//                ).allowMainThreadQueries()
//                    .build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
}