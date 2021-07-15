package com.example.unsplash.ui.collection.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.R
import com.example.unsplash.data.model.Collection
import com.example.unsplash.databinding.ItemLayoutCollectionBinding
import com.example.unsplash.utils.OnItemClickListener

class CollectionAdapter : ListAdapter<Collection, CollectionViewHolder>(Companion) {

    private var onClickItemViewHolder: ((Collection) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val binding = DataBindingUtil.inflate<ItemLayoutCollectionBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_layout_collection, parent,
            false
        )
        return CollectionViewHolder(binding) {
            onClickItemViewHolder?.invoke(it)
        }.apply {
            registerOnItemClickListener()
        }
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    fun setOnclickItem(onClick: ((Collection) -> Unit)) {
        this.onClickItemViewHolder = onClick
    }

    companion object : DiffUtil.ItemCallback<Collection>() {
        override fun areItemsTheSame(oldItem: Collection, newItem: Collection): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Collection, newItem: Collection): Boolean {
            return oldItem == newItem
        }

    }
}

class CollectionViewHolder(
    private val binding: ItemLayoutCollectionBinding,
    private val onClickItemViewHolder: ((Collection) -> Unit)
) : RecyclerView.ViewHolder(binding.root), OnItemClickListener<Collection> {

    override fun onItemClick(item: Collection) {
        onClickItemViewHolder(item)
    }

    fun onBind(collection: Collection) {
        binding.run {
            this.collection = collection
            executePendingBindings()
        }
    }

    fun registerOnItemClickListener() {
        binding.onItemClickListener = this
    }
}
