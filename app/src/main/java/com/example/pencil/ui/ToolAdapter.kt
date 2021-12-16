package com.example.pencil.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pencil.databinding.ToolItemBinding
import com.example.pencil.ui.data.Tool


class ToolAdapter(private val clickListener: ToolClickListeners) :
    RecyclerView.Adapter<ToolAdapter.ToolViewHolder>() {


    val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Tool>() {
        override fun areItemsTheSame(oldItem: Tool, newItem: Tool): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Tool, newItem: Tool): Boolean {
            return oldItem == newItem
        }
    })

    inner class ToolViewHolder(private val binding: ToolItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tool: Tool) {

            binding.apply {
                categoryIcon.setImageDrawable(tool.icon)
                root.setOnClickListener {
                    clickListener.getTool(tool)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolViewHolder {
        val binding = ToolItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ToolViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToolViewHolder, position: Int) {
        val order = differ.currentList[position]
        holder.bind(order)
    }

    override fun getItemCount() = differ.currentList.size
}
