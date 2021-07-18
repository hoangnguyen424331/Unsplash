package com.example.unsplash.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.data.model.Topic
import com.example.unsplash.databinding.ItemLayoutHomeBinding
import com.example.unsplash.databinding.ItemLoadMoreBinding
import com.example.unsplash.utils.TopicDiffUtilCallBack

class HomeTopicsAdapter(private val onItemTopicClick: (Topic) -> Unit) :
    ListAdapter<Topic, RecyclerView.ViewHolder>(TopicDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_LOADING) {
            val binding =
                ItemLoadMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LoadItemViewHolder(binding)
        } else {
            val binding =
                ItemLayoutHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ItemViewHolder(binding, onItemTopicClick)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.onBind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).id == "") VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    class ItemViewHolder(
        private val binding: ItemLayoutHomeBinding,
        val onItemTopicClick: (Topic) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(topic: Topic) {
            binding.apply {
                this.topic = topic
                executePendingBindings()
                cardView.setOnClickListener { onItemTopicClick(topic) }
            }
        }
    }

    class LoadItemViewHolder(binding: ItemLoadMoreBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        const val VIEW_TYPE_LOADING = 0
        const val VIEW_TYPE_ITEM = 1
    }
}
