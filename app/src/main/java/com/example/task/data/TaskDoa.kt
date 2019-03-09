package com.example.task.data


import android.arch.persistence.room.*


@Dao
interface TaskDoa {

    @Query("SELECT * FROM Task")
    fun getAllTasks(): List<Task>?


    @Insert
    fun insertAllTasks(task: Task)

//    @Delete
//    fun deleteTask(task: Task)

    @Query("DELETE FROM Task WHERE Id = :Id")
    fun deleteTaskById(Id: Int)

//    @Query("SELECT * FROM Task WHERE Id =:Id")
//    fun fetchTasksById(Id: Int)



//    @Insert
//    fun insertMultipleTasks(taskList: List<Task>)
//
//
//    @Update
//    fun updateTask(task: Task)
//


}