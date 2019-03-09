package com.example.task


import android.content.Context
import android.os.Vibrator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.task.data.Task

class TaskAdapter(
    var tasks: List<Task>?,
    var context: Context,
    private val clickListener: TaskSelectionRecyclerViewClickListener
) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

//     val tasks : ArrayList<String> = ArrayList()

    companion object {
        val INTENT_TASK_KEY = "task"
    }

    fun updateTask(taskList: List<Task>) {
        this.tasks = taskList
        notifyDataSetChanged()
//        tasks.add(tasks)
    }

    interface TaskSelectionRecyclerViewClickListener {
        fun taskItemLongClicked(task: Task)
        fun taskItemClicked(task: Task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_selection_view_holder, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks!!.size
    }

    override fun onBindViewHolder(holder: TaskAdapter.ViewHolder, position: Int) {
        holder.listPosition.text = (position + 1).toString()
        holder.listTitle.text = tasks!!.get(position).title
        holder.itemView.setOnClickListener {
            clickListener.taskItemClicked(tasks!!.get(position))

        }

        holder.itemView.setOnLongClickListener {
            val vibratorService = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(500)

            clickListener.taskItemLongClicked(tasks!![position])
            //showDeleteDialog(tasks!![position].Id)
            true
        }
    }


//    private fun showTaskDetail(task: Task){
//        val taskDetailIntent = Intent(, TaskDetailActivity::class.java)
//        taskDetailIntent.putExtra(TaskAdapter.INTENT_TASK_KEY, task)
//        startActivity(taskDetailIntent)
//
//    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val listPosition = itemView.findViewById(R.id.itemNumber) as TextView
        val listTitle = itemView.findViewById(R.id.itemString) as TextView
    }


}
