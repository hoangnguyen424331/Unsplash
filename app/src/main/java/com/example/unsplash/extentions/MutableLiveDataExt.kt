package com.example.unsplash.extentions

import androidx.lifecycle.MutableLiveData

operator fun <T> MutableLiveData<MutableList<T>>.plusAssign(item: T) {
    val value = this.value ?: mutableListOf()
    value.add(item)
    this.value = value
}

operator fun <T> MutableLiveData<MutableList<T>>.plusAssign(item: MutableList<T>) {
    val value = this.value ?: mutableListOf()
    value.addAll(item)
    this.postValue(value)
}
