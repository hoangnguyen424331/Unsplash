package com.example.unsplash.ui.photo_collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash.data.model.PhotoCollection
import com.example.unsplash.data.repository.PhotoRepository
import com.example.unsplash.extentions.plusAssign
import com.example.unsplash.utils.Constant.API_COLLECTION
import com.example.unsplash.utils.Constant.DEFAULT_ID
import com.example.unsplash.utils.Constant.DEFAULT_PAGE
import com.example.unsplash.utils.LoadMoreRecyclerViewListener
import com.example.unsplash.utils.RefreshRecyclerViewListener
import com.example.unsplash.utils.Resource
import kotlinx.coroutines.launch

class PhotoCollectionsViewModel(private val photoRepository: PhotoRepository) :
    ViewModel(),
    LoadMoreRecyclerViewListener, RefreshRecyclerViewListener {

    private var currentPosition = DEFAULT_PAGE
    private var id: String = DEFAULT_ID
    private var api: String = DEFAULT_ID

    private val _resource = MutableLiveData<Resource<LiveData<MutableList<PhotoCollection>>>>()
    val resource: LiveData<Resource<LiveData<MutableList<PhotoCollection>>>>
        get() = _resource

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _photoCollection = MutableLiveData<MutableList<PhotoCollection>>(mutableListOf())
    private val photoCollection: LiveData<MutableList<PhotoCollection>>
        get() = _photoCollection

    override fun onLoadData() {
        _isLoading.value = true
        fetchCollections(id, api)
    }

    override fun onRefreshData() {
        _photoCollection.value?.clear()
        currentPosition = DEFAULT_PAGE
        fetchCollections(id, api)
    }

    fun fetchCollections(id: String, api: String) {
        viewModelScope.launch {
            try {
                this@PhotoCollectionsViewModel.id = id
                this@PhotoCollectionsViewModel.api = api
                _photoCollection.plusAssign(
                    if (api == API_COLLECTION) {
                        photoRepository.getPhotosCollections(id, currentPosition)
                    } else {
                        photoRepository.getPhotosTopic(id, currentPosition)
                    }
                )
                _resource.postValue(Resource.success(data = photoCollection))
                currentPosition++
                _isLoading.value = false
            } catch (e: Exception) {
                _resource.postValue(Resource.error(data = null, message = e.message.toString()))
            }
        }
    }
}
