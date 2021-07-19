package com.example.unsplash.ui.photo_collection.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.R
import com.example.unsplash.data.model.PhotoCollection
import com.example.unsplash.databinding.ItemLayoutPhotoBinding
import com.example.unsplash.utils.OnItemClickListener

class PhotoCollectionAdapter : ListAdapter<PhotoCollection, PhotoCollectionsViewHolder>(Companion) {

    private var onClickItemViewHolder: ((PhotoCollection) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoCollectionsViewHolder {
        val binding = DataBindingUtil.inflate<ItemLayoutPhotoBinding>(
            LayoutInflater.from(parent.context), R.layout.item_layout_photo, parent, false
        )
        return PhotoCollectionsViewHolder(binding) {
            onClickItemViewHolder?.invoke(it)
        }.apply {
            registerOnItemClickListener()
        }
    }

    override fun onBindViewHolder(holder: PhotoCollectionsViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    fun setOnClick(onClick: ((PhotoCollection) -> Unit)) {
        this.onClickItemViewHolder = onClick
    }

    companion object : DiffUtil.ItemCallback<PhotoCollection>() {

        override fun areItemsTheSame(oldItem: PhotoCollection, newItem: PhotoCollection): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PhotoCollection,
            newItem: PhotoCollection
        ): Boolean {
            return oldItem == newItem
        }
    }
}

class PhotoCollectionsViewHolder(
    private val binding: ItemLayoutPhotoBinding,
    private val onClickItemViewHolder: ((PhotoCollection) -> Unit)
) : RecyclerView.ViewHolder(binding.root), OnItemClickListener<PhotoCollection> {

    override fun onItemClick(item: PhotoCollection) {
        onClickItemViewHolder(item)
    }

    fun onBind(photoCollection: PhotoCollection) {
        binding.run {
            this.photoCollection = photoCollection
            executePendingBindings()
        }
    }

    fun registerOnItemClickListener() {
        binding.onItemClickListener = this
    }
}
