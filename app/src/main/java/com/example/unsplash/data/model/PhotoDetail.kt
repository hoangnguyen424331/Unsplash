package com.example.unsplash.data.model

import com.google.gson.annotations.SerializedName

data class PhotoDetail(
    @SerializedName("id")
    val id: String?,
    @SerializedName("urls")
    val urlPhoto: UrlPhotoDetail,
    @SerializedName("user")
    val authorInformation: AuthorInformation,
    @SerializedName("views")
    val views: Int?,
    @SerializedName("downloads")
    val downloads: Int?,
    @SerializedName("likes")
    val likes: Int?,
)
