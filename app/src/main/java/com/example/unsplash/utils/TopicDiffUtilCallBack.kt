package com.example.unsplash.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.unsplash.data.model.Topic

class TopicDiffUtilCallBack : DiffUtil.ItemCallback<Topic?>() {

    override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
        return oldItem == newItem
    }
}
