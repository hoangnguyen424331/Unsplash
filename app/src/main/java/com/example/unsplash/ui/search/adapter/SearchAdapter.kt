@file:Suppress("DEPRECATION")

package com.example.unsplash.ui.search.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.unsplash.ui.search.search_collection.SearchCollectionFragment
import com.example.unsplash.ui.search.search_photo.SearchPhotoFragment
import com.example.unsplash.utils.TypeSearch

@Suppress("DEPRECATION")
class SearchAdapter(
    fragmentManager: FragmentManager,
    private var fragment: List<Fragment> = arrayListOf()
) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount() = fragment.size

    override fun getItem(position: Int) = fragment[position]

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            TypeSearch.PHOTO.ordinal -> TypeSearch.PHOTO.name
            TypeSearch.COLLECTION.ordinal -> TypeSearch.COLLECTION.name
            else -> TypeSearch.PHOTO.name
        }
    }

    fun setKeyword(keyword: String) {
        fragment.forEach {
            when (it) {
                is SearchCollectionFragment -> it.searchCollection(keyword)
                is SearchPhotoFragment -> it.searchPhoto(keyword)
            }
        }
    }

    fun addFragment(fragment: List<Fragment>) {
        this.fragment = fragment
    }
}
