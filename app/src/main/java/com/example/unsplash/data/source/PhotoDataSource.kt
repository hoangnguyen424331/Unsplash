package com.example.unsplash.data.source

import com.example.unsplash.data.model.Collection
import com.example.unsplash.data.model.PhotoCollection

class PhotoDataSource {

    interface Remote {

        suspend fun getCollections(page: Int): MutableList<Collection>

        suspend fun getPhotosCollection(id: String, page: Int): MutableList<PhotoCollection>
    }
}
