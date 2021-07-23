package com.example.unsplash.data.model

import com.google.gson.annotations.SerializedName

data class SearchPhoto(
    @SerializedName("results")
    val photos: MutableList<PhotoCollection> = arrayListOf()
)
