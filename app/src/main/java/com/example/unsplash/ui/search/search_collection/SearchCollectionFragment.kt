package com.example.unsplash.ui.search.search_collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.unsplash.databinding.FragmentCollectionBinding

class SearchCollectionFragment: Fragment() {

    lateinit var binding: FragmentCollectionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun searchCollection(keyWord: String) {}

    companion object {
        fun newInstance() = SearchCollectionFragment()
    }
}
