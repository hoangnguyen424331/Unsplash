package com.example.unsplash.data.source.local

import com.example.unsplash.data.source.PhotoDataSource
import com.example.unsplash.data.source.local.sqlite.dao.ImageDao
import com.example.unsplash.data.source.local.sqlite.entity.ImageLocal

class PhotoLocalDataSource(private val imageDao: ImageDao) : PhotoDataSource.Local {

    override suspend fun getImages(): List<ImageLocal> = imageDao.getImages()

    override suspend fun insertImages(imageLocal: ImageLocal) = imageDao.insertImages(imageLocal)

    override suspend fun deleteImages(imageLocal: ImageLocal) = imageDao.deleteImages(imageLocal)

    override suspend fun getFavoriteImages(imageId: String?): ImageLocal =
        imageDao.getFavoriteImages(imageId)
}
