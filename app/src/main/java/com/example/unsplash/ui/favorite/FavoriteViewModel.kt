package com.example.unsplash.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash.data.repository.PhotoRepository
import com.example.unsplash.data.source.local.sqlite.entity.ImageLocal
import com.example.unsplash.utils.LoadMoreRecyclerViewListener
import com.example.unsplash.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val photoRepository: PhotoRepository
) : ViewModel(),LoadMoreRecyclerViewListener {

    private val _favoriteImage = MutableLiveData<Resource<List<ImageLocal>>>()
    val favoriteImage: LiveData<Resource<List<ImageLocal>>>
        get() = _favoriteImage

    fun getImages() {
        viewModelScope.launch(Dispatchers.IO) {
            _favoriteImage.postValue(Resource.loading(null))
            try {
                _favoriteImage.postValue(Resource.success(photoRepository.getImages()))
            } catch (e: Exception) {
                _favoriteImage.postValue(Resource.error(null, message = e.message.toString()))
            }
        }
    }

    override fun onLoadData() {
        getImages()
    }
}
