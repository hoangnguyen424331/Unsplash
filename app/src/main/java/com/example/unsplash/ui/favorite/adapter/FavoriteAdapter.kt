package com.example.unsplash.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.R
import com.example.unsplash.data.source.local.sqlite.entity.ImageLocal
import com.example.unsplash.databinding.ItemFavoriteBinding

class FavoriteAdapter : ListAdapter<ImageLocal, FavoriteViewHolder>(Companion) {

    private var onItemFavoriteClick: ((ImageLocal) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = DataBindingUtil.inflate<ItemFavoriteBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_favorite, parent, false
        )
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    fun setOnClickItem(onClick: ((ImageLocal) -> Unit)) {
        this.onItemFavoriteClick = onClick
    }

    companion object : DiffUtil.ItemCallback<ImageLocal>() {

        override fun areItemsTheSame(oldItem: ImageLocal, newItem: ImageLocal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageLocal, newItem: ImageLocal): Boolean {
            return oldItem == newItem
        }
    }
}

class FavoriteViewHolder(
    private val binding: ItemFavoriteBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(imageLocal: ImageLocal) {
        binding.run {
            this.imageLocal = imageLocal
            executePendingBindings()
        }
    }
}
