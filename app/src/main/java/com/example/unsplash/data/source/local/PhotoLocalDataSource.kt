package com.example.unsplash.data.source.local

import com.example.unsplash.data.source.PhotoDataSource
import com.example.unsplash.data.source.local.sqlite.dao.ImageDao
import com.example.unsplash.data.source.local.sqlite.entity.ImageLocal

class PhotoLocalDataSource(private val imageDao: ImageDao) : PhotoDataSource.Local {

    override suspend fun getImages(): List<ImageLocal> = imageDao.getImages()
}
