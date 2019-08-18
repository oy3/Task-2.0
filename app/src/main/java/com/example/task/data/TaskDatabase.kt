package com.example.task.data


import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
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