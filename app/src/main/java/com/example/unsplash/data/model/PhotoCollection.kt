package com.example.unsplash.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoCollection(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("likes")
    val likes: Int = 0,
    @SerializedName("urls")
    val image: Image
) : Parcelable
