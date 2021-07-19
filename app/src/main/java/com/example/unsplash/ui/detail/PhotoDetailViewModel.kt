package com.example.unsplash.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash.data.model.PhotoDetail
import com.example.unsplash.data.repository.PhotoRepository
import com.example.unsplash.data.source.local.sqlite.entity.ImageLocal
import com.example.unsplash.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class PhotoDetailViewModel(private val photoRepository: PhotoRepository) : ViewModel() {

    private val _photoDetail = MutableLiveData<Resource<PhotoDetail>>()
    val photoDetail: MutableLiveData<Resource<PhotoDetail>>
        get() = _photoDetail

    private val _isFavorite = MutableLiveData<Resource<Boolean>>()
    val isFavorite: MutableLiveData<Resource<Boolean>>
        get() = _isFavorite

    fun getPhotoDetail(id: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            _photoDetail.postValue(Resource.loading(null))
            try {
                _photoDetail.postValue(Resource.success(photoRepository.getPhotoDetail(id)))
            } catch (e: Exception) {
                _photoDetail.postValue(Resource.error(null, e.message.toString()))
            }
        }
    }

    fun insertImages(imageLocal: ImageLocal) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                photoRepository.insertImages(imageLocal)
                _isFavorite.postValue(Resource.success(true))
            } catch (e: Exception) {
                _isFavorite.postValue(Resource.error(null, e.message.toString()))
            }
        }
    }

    fun deleteImages(imageLocal: ImageLocal) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                photoRepository.deleteImages(imageLocal)
                _isFavorite.postValue(Resource.success(false))
            } catch (e: Exception) {
                _isFavorite.postValue(Resource.error(null, e.message.toString()))
            }
        }
    }

    fun favoriteImages(id: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (photoRepository.getFavoriteImages(id).urlImage.isNullOrEmpty()) {
                    _isFavorite.postValue(Resource.success(false))
                } else _isFavorite.postValue(Resource.success(true))
            } catch (e: Exception) {
                _isFavorite.postValue(Resource.error(null, e.message.toString()))
            }
        }
    }
}
