package com.example.unsplash.data.source.remote

import com.example.unsplash.data.source.PhotoDataSource

class PhotoRemoteDataSource(private val apiService: APIService) : PhotoDataSource.Remote {

    override suspend fun getCollections(page: Int) = apiService.getCollections(page = page)

    override suspend fun getPhotosCollection(id: String, page: Int) =
        apiService.getPhotosCollections(
            id = id, page = page
        )
}
