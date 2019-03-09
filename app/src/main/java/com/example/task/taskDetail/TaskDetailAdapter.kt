package com.example.task.taskDetail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.task.R
import com.example.task.data.Task
import kotlinx.android.synthetic.main.activity_task_detail.view.*

class TaskDetailAdapter(var tasks: List<Task>?) : RecyclerView.Adapter<TaskDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskDetailAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_task_detail, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks!!.size
    }

    override fun onBindViewHolder(holder: TaskDetailAdapter.ViewHolder, position: Int) {
//        holder.detailTask.text = (position).toString()
////        holder.detailDescription.text = tasks!!.get(position).title

        holder.bind(tasks!![position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var task: Task

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(task: Task) {
            this.task = task
            itemView.detailTask.text = task.title
            itemView.detailDescription.text = task.description
        }

        override fun onClick(view: View) {
            val context = view.context
            context.startActivity(TaskDetailActivity.newIntent(context, task))
        }

    }
}