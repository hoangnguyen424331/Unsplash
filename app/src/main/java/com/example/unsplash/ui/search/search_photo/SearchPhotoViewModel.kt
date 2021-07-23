package com.example.unsplash.ui.search.search_photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash.data.model.PhotoCollection
import com.example.unsplash.data.repository.PhotoRepository
import com.example.unsplash.extentions.plusAssign
import com.example.unsplash.utils.Constant.DEFAULT_PAGE
import com.example.unsplash.utils.Constant.DEFAULT_STRING
import com.example.unsplash.utils.LoadMoreRecyclerViewListener
import com.example.unsplash.utils.RefreshRecyclerViewListener
import com.example.unsplash.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchPhotoViewModel(private val photoRepository: PhotoRepository) : ViewModel(),
    LoadMoreRecyclerViewListener, RefreshRecyclerViewListener {

    private var currentPosition = DEFAULT_PAGE
    private var keyword: String = DEFAULT_STRING

    private val _resource = MutableLiveData<Resource<LiveData<MutableList<PhotoCollection>>>>()
    val resource: LiveData<Resource<LiveData<MutableList<PhotoCollection>>>>
        get() = _resource

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _photos = MutableLiveData<MutableList<PhotoCollection>>(mutableListOf())
    val photos: LiveData<MutableList<PhotoCollection>>
        get() = _photos

    override fun onLoadData() {
        _isLoading.value = true
        searchPhotos(keyword)
    }

    override fun onRefreshData() {
        _photos.value?.clear()
        currentPosition = DEFAULT_PAGE
        searchPhotos(keyword)
    }

    private fun searchPhotos(keyword: String) {
        viewModelScope.launch {
            try {
                _photos.plusAssign(photoRepository.searchPhotos(keyword, currentPosition).photos)
                _resource.postValue(Resource.success(photos))
                currentPosition++
                _isLoading.value = false
            } catch (e: Exception) {
                _resource.postValue(Resource.error(null, e.message.toString()))
            }
        }
    }

    fun searchFirstTime(keyword: String) {
        this.keyword = keyword
        _photos.value?.clear()
        searchPhotos(keyword)
    }
}
