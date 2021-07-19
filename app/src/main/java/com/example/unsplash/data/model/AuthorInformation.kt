package com.example.unsplash.data.model

import com.google.gson.annotations.SerializedName

data class AuthorInformation(
    @SerializedName("name")
    val name: String?,
    @SerializedName("profile_image")
    val avatar: AvatarAuthor
)

data class AvatarAuthor(
    @SerializedName("small")
    val smallAvatar: String?
)
