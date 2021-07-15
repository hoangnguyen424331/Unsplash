package com.example.unsplash.extentions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.Target

fun ImageView.loadFromUrl(url: String) {
    Glide.with(this.context.applicationContext)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .skipMemoryCache(false)
        .into(this)
}

fun ImageView.loadUrlStaggered(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .fitCenter()
        .override(Target.SIZE_ORIGINAL)
        .into(this)
}
