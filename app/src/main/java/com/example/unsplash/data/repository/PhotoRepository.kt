package com.example.unsplash.data.repository

import com.example.unsplash.data.source.PhotoDataSource
import com.example.unsplash.utils.Constant.DEFAULT_PAGE

class PhotoRepository(private val remote: PhotoDataSource.Remote) {

    suspend fun getCollection(page: Int = DEFAULT_PAGE) = remote.getCollections(page = page)

    suspend fun getPhotosCollections(id: String, page: Int = DEFAULT_PAGE) =
        remote.getPhotosCollection(
            id = id,
            page = page
        )

    suspend fun getTopics(page: Int = DEFAULT_PAGE) = remote.getTopics(page)

    suspend fun getRandomPhotos() = remote.getRandomPhotos()
}
