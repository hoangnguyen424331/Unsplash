package com.example.unsplash.data.source.remote

import com.example.unsplash.data.model.Collection
import com.example.unsplash.data.model.PhotoCollection
import com.example.unsplash.utils.Constant.API_KEY
import com.example.unsplash.utils.Constant.DEFAULT_PAGE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionAPI {

    @GET("collections")
    suspend fun getCollections(
        @Query("client_id") keyApi: String = API_KEY ,
        @Query("page") page: Int = DEFAULT_PAGE
    ): MutableList<Collection>

    @GET("/collections/{id}/photos")
    suspend fun getPhotosCollections(
        @Query("client_id") keyApi: String = API_KEY,
        @Path("id") id: String,
        @Query("page") page: Int = DEFAULT_PAGE
    ): MutableList<PhotoCollection>
}
