package com.example.unsplash.data.source.remote

import com.example.unsplash.data.model.Collection
import com.example.unsplash.data.model.PhotoCollection
import com.example.unsplash.data.model.PhotoDetail
import com.example.unsplash.data.model.Topic
import com.example.unsplash.utils.Constant.DEFAULT_PAGE
import com.example.unsplash.utils.Constant.RANDOM_ITEM_COUNT
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  APIService {

    @GET("/collections")
    suspend fun getCollections(
        @Query("page") page: Int
    ): MutableList<Collection>

    @GET("/collections/{id}/photos")
    suspend fun getPhotosCollections(
        @Path("id") id: String,
        @Query("page") page: Int = DEFAULT_PAGE
    ): MutableList<PhotoCollection>

    @GET("/topics")
    suspend fun getTopics(
        @Query("page") page: Int = DEFAULT_PAGE
    ): MutableList<Topic>

    @GET("/photos/random")
    suspend fun getRandomPhotos(
        @Query("count") count: Int = RANDOM_ITEM_COUNT
    ): MutableList<PhotoCollection>

    @GET("/photos/{id}")
    suspend fun getPhotoDetail(
        @Query("id") id: String?
    ): PhotoDetail
}
