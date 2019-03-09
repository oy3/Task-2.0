package com.example.task.taskDetail

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.task.R
import com.example.task.data.Task
import kotlinx.android.synthetic.main.activity_task_detail.*

class TaskDetailActivity : AppCompatActivity() {


    companion object {
        val INTENT_TASK_KEY = "task"

        fun newIntent(context: Context, task: Task): Intent {
            val intent = Intent(context, TaskDetailActivity::class.java)
            intent.putExtra(INTENT_TASK_KEY, task)
            return intent
        }
    }

   private val task  by lazy { intent.getParcelableExtra<Task>(INTENT_TASK_KEY) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

//        task = intent.getParcelableExtra(MainActivity.INTENT_TASK_KEY)
        title = task.title
        detailTask.text = task.title
        detailDescription.text = task.description
    }
}
