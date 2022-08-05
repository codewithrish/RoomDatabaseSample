package com.codewithrish.roomdatabasesample.presentation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codewithrish.roomdatabasesample.R
import com.codewithrish.roomdatabasesample.data.db.entity.Task
import de.hdodenhof.circleimageview.CircleImageView

class TaskAdapter: ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffUtil()) {

    var deleteTask: ((Task) -> Unit)? = null
    var updateTask: ((Task) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TaskViewHolder(inflater.inflate(R.layout.list_item_task, parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class TaskViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val tvTask = view.findViewById<TextView>(R.id.tv_task)
        private val tvImportance = view.findViewById<TextView>(R.id.tv_importance)
        private val tvFirstLetter = view.findViewById<TextView>(R.id.tv_first_letter)
        private val btnDelete = view.findViewById<ImageView>(R.id.btn_delete)
        private val btnEdit = view.findViewById<ImageView>(R.id.btn_edit)
        private val civImportance = view.findViewById<CircleImageView>(R.id.civ_importance)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(task: Task) {

            val context = itemView.context

            tvTask.text = task.task
            tvImportance.text = task.importance
            tvFirstLetter.text = task.task.substring(0,1).uppercase()


            when (task.importance) {
                context.getString(R.string.important) -> {
                    civImportance.setImageDrawable(context.resources.getDrawable(R.drawable.important_circle, null))
                }
                context.getString(R.string.normal) -> {
                    civImportance.setImageDrawable(context.resources.getDrawable(R.drawable.normal_circle, null))
                }
                context.getString(R.string.not_important) -> {
                    civImportance.setImageDrawable(context.resources.getDrawable(R.drawable.not_important_circle, null))
                }
            }

            btnDelete.setOnClickListener {
                deleteTask?.invoke(task)
            }

            btnEdit.setOnClickListener {
                updateTask?.invoke(task)
            }
        }
    }

    class TaskDiffUtil : DiffUtil.ItemCallback<Task>()  {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

    }

}