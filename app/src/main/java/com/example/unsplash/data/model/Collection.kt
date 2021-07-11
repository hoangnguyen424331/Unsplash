package com.example.unsplash.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Collection(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("total_photos")
    val totalPhotos: Int = 0,
    @SerializedName("cover_photo")
    val photoCollection: PhotoCollection
): Parcelable
