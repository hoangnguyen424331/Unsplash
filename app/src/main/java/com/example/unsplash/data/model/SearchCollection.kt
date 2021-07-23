package com.example.unsplash.data.model

import com.google.gson.annotations.SerializedName

data class SearchCollection(
    @SerializedName("results")
    val collections: MutableList<Collection> = arrayListOf()
)
