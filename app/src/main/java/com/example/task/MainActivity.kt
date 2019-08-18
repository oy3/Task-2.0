package com.example.task

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.task.data.Task
import com.example.task.data.TaskDatabase
import com.example.task.taskDetail.TaskDetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    TaskAdapter.TaskSelectionRecyclerViewClickListener {
    lateinit var db: TaskDatabase


    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TaskAdapter


    companion object {
        val INTENT_TASK_KEY = "task"
    }


    override fun taskItemLongClicked(task: Task) {
        showDeleteDialog(task.Id)
    }


    private fun showDeleteDialog(id: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Task")
        builder.setMessage("Do you want to delete task?")
        builder.setPositiveButton("YES") { dialog, which ->


            db.taskDao().deleteTaskById(id)
//            adapter.updateTask(taskList!!)
            Toast.makeText(this, "Deleted task successfully!!", Toast.LENGTH_SHORT).show()
            refreshList()
        }
        builder.setNegativeButton("No") { dialog, which ->
            refreshList()
        }

        val dialog: AlertDialog = builder.create()

        dialog.show()

    }

    private fun showHelpDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Help")
        builder.setMessage(
            "1. To add task: Click on the add button, a new page will open then add new task." + "\n\n" +
                    "2. To open task: Click on task in the list." + "\n\n" +
                    "3. To delete task: Hold on the task you wish to delete for 2 or more seconds, you will feel a vibration then follow the dialog that pops up." + "\n\n" +
                    "4. To exit the application: Tap your back button twice."

        )
        builder.setPositiveButton("Okay") { dialog, which ->
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

//        TaskDatabase.getDatabase(this)


        db = Room.databaseBuilder(
            this.applicationContext,
            TaskDatabase::class.java,
            "Task_database"
        ).allowMainThreadQueries()
            .build()

        val tasksList: List<Task>? = db.taskDao().getAllTasks()

        recyclerView = findViewById(R.id.lists_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TaskAdapter(
            tasksList, this,
            this
        )


        fab.setOnClickListener { view ->
            startActivity(Intent(this@MainActivity, CreateTask::class.java))
//           showTaskDetail()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                showHelpDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showTaskDetail(task: Task) {
        val taskDetailIntent = Intent(this, TaskDetailActivity::class.java)
        taskDetailIntent.putExtra(INTENT_TASK_KEY, task)
        startActivity(taskDetailIntent)
    }

    override fun taskItemClicked(task: Task) {
        showTaskDetail(task)
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Tap back again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun refreshList() {
        val taskList: List<Task>? = db.taskDao().getAllTasks()
        recyclerView.adapter = TaskAdapter(
            taskList, this,
            this
        )
    }
}
