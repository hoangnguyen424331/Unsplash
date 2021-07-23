package com.example.unsplash.ui.search.search_collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash.data.model.Collection
import com.example.unsplash.data.repository.PhotoRepository
import com.example.unsplash.extentions.plusAssign
import com.example.unsplash.utils.Constant.DEFAULT_PAGE
import com.example.unsplash.utils.Constant.DEFAULT_STRING
import com.example.unsplash.utils.LoadMoreRecyclerViewListener
import com.example.unsplash.utils.RefreshRecyclerViewListener
import com.example.unsplash.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchCollectionViewModel(private val photoRepository: PhotoRepository) : ViewModel(),
    LoadMoreRecyclerViewListener, RefreshRecyclerViewListener {

    private var currentPosition = DEFAULT_PAGE
    private var keyword = DEFAULT_STRING

    private val _resource = MutableLiveData<Resource<LiveData<MutableList<Collection>>>>()
    val resource: LiveData<Resource<LiveData<MutableList<Collection>>>>
        get() = _resource

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _collection = MutableLiveData<MutableList<Collection>>(mutableListOf())
    val collection: LiveData<MutableList<Collection>>
        get() = _collection

    override fun onLoadData() {
        _isLoading.value = true
        searchCollections(keyword)
    }

    override fun onRefreshData() {
        _collection.value?.clear()
        currentPosition = DEFAULT_PAGE
        searchCollections(keyword)
    }

    private fun searchCollections(keyword: String) {
        viewModelScope.launch {
            try {
                _collection.plusAssign(
                    photoRepository.searchCollections(
                        keyword,
                        currentPosition
                    ).collections
                )
                _resource.postValue(Resource.success(collection))
            } catch (e: Exception) {
                _resource.postValue(Resource.error(null, e.message.toString()))
            }
        }
    }

    fun searchFirstTime(keyword: String) {
        this.keyword = keyword
        _collection.value?.clear()
        searchCollections(keyword)
    }
}
