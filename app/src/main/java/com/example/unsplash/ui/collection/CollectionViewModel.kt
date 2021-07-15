package com.example.unsplash.ui.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash.data.model.Collection
import com.example.unsplash.data.repository.PhotoRepository
import com.example.unsplash.extentions.plusAssign
import com.example.unsplash.utils.Constant.DEFAULT_PAGE
import com.example.unsplash.utils.LoadMoreRecyclerViewListener
import com.example.unsplash.utils.RefreshRecyclerViewListener
import com.example.unsplash.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class CollectionViewModel(private val photoRepository: PhotoRepository) : ViewModel(),
    LoadMoreRecyclerViewListener, RefreshRecyclerViewListener {

    private var currentPosition = DEFAULT_PAGE
    private val _resource = MutableLiveData<Resource<LiveData<MutableList<Collection>>>>()
    val resource: LiveData<Resource<LiveData<MutableList<Collection>>>>
        get() = _resource

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _collections = MutableLiveData<MutableList<Collection>>(mutableListOf())
    val collection: LiveData<MutableList<Collection>>
        get() = _collections

    init {
        fetchCollections()
    }

    override fun onLoadData() {
        _isLoading.value = true
        fetchCollections()
    }

    override fun onRefreshData() {
        _collections.value?.clear()
        currentPosition = DEFAULT_PAGE
        fetchCollections()
    }

    private fun fetchCollections() {
        viewModelScope.launch {
            try {
                _collections.plusAssign(photoRepository.getCollection(currentPosition))
                _resource.postValue(Resource.success(data = collection))
                currentPosition++
                _isLoading.value = false
            } catch (e: Exception) {
                _resource.postValue(Resource.error(data = null, message = e.message.toString()))
            }
        }
    }
}
