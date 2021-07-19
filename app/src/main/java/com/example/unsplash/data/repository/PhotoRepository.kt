package com.example.unsplash.data.repository

import com.example.unsplash.data.source.PhotoDataSource
import com.example.unsplash.data.source.local.sqlite.entity.ImageLocal
import com.example.unsplash.utils.Constant.DEFAULT_PAGE

class PhotoRepository(private val remote: PhotoDataSource.Remote,
private val local: PhotoDataSource.Local) {

    suspend fun getCollection(page: Int = DEFAULT_PAGE) = remote.getCollections(page = page)

    suspend fun getPhotosCollections(id: String, page: Int = DEFAULT_PAGE) =
        remote.getPhotosCollection(
            id = id,
            page = page
        )

    suspend fun getTopics(page: Int = DEFAULT_PAGE) = remote.getTopics(page)

    suspend fun getRandomPhotos() = remote.getRandomPhotos()

    suspend fun getPhotoDetail(id: String?) = remote.getPhotoDetail(id)

    suspend fun getImages() = local.getImages()

    suspend fun insertImages(imageLocal: ImageLocal) = local.insertImages(imageLocal)

    suspend fun deleteImages(imageLocal: ImageLocal) = local.deleteImages(imageLocal)

    suspend fun getFavoriteImages(id: String?) = local.getFavoriteImages(id)
}
