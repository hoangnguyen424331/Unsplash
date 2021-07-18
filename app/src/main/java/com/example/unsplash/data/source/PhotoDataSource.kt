package com.example.unsplash.data.source

import com.example.unsplash.data.model.Collection
import com.example.unsplash.data.model.PhotoCollection
import com.example.unsplash.data.model.Topic

class PhotoDataSource {

    interface Remote {

        suspend fun getCollections(page: Int): MutableList<Collection>

        suspend fun getPhotosCollection(id: String, page: Int): MutableList<PhotoCollection>

        suspend fun getTopics(page: Int): MutableList<Topic>

        suspend fun getRandomPhotos(): MutableList<PhotoCollection>
    }
}
