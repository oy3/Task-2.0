package com.example.task

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.task.data.Task
import com.example.task.data.TaskDatabase
import kotlinx.android.synthetic.main.create_task.*

class CreateTask : AppCompatActivity() {

    private var TAG: String = "CreateTask"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_task)


        val db = Room.databaseBuilder(
            this.applicationContext,
            TaskDatabase::class.java,
            "Task_database"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        button.setOnClickListener { view ->
            if (task_name.text.isNullOrEmpty() || task_description.text.isNullOrEmpty()) {
                if (task_name.text.isNullOrEmpty()) {
                    task_name.error = "Please input task"
                } else {
                    task_description.error = "Please input task description"
                }
            } else {

                Log.d(
                    TAG, "onClick: Task:  ${task_name.text.toString()} " +
                            ", Description: ${task_description.text.toString()} "
                )
                db.taskDao().insertAllTasks(Task(task_name.text.toString(), task_description.text.toString()))
//            showTaskDetail(task = Task.createFromParcel(source = Parcel.obtain()))
                startActivity(Intent(this@CreateTask, MainActivity::class.java))
            }
        }
    }

//    private fun showTaskDetail(task: Task){
//        val taskDetailIntent = Intent(this, TaskDetailActivity::class.java)
//        taskDetailIntent.putExtra(MainActivity.INTENT_TASK_KEY, task)
//        startActivity(taskDetailIntent)
//
//    }
}