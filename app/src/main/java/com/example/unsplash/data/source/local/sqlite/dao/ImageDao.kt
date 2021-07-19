package com.example.unsplash.data.source.local.sqlite.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.unsplash.data.source.local.sqlite.entity.ImageLocal

@Dao
interface ImageDao {

    @Query("SELECT * FROM imageLocal")
    suspend fun getImages(): List<ImageLocal>

    @Insert
    suspend fun insertImages(imageLocal: ImageLocal)

    @Delete
    suspend fun deleteImages(imageLocal: ImageLocal)

    @Query("SELECT * FROM imageLocal WHERE id LIKE :imageId")
    suspend fun getFavoriteImages(imageId: String?): ImageLocal
}
