package com.example.unsplash.data.source.local.sqlite.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ImageLocal(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "url_image")
    val urlImage: String?,
    @ColumnInfo(name = "author_name")
    val authorName: String?,
    @ColumnInfo(name = "url_author_avatar")
    val authorAvatar: String?,
    @ColumnInfo(name = "views")
    val Views: Int?,
    @ColumnInfo(name = "likes")
    val Likes: Int?,
    @ColumnInfo(name = "downloads")
    val downloads: Int?
) : Parcelable
