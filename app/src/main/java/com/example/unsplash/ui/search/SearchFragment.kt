package com.example.unsplash.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.unsplash.databinding.FragmentSearchBinding
import com.example.unsplash.ui.search.adapter.SearchAdapter
import com.example.unsplash.ui.search.search_collection.SearchCollectionFragment
import com.example.unsplash.ui.search.search_photo.SearchPhotoFragment

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchAdapter by lazy { SearchAdapter(childFragmentManager) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTab()
        handEvent()
        binding()
    }

    private fun initTab() {
        val listFragment = listOf(
            SearchPhotoFragment.newInstance(),
            SearchCollectionFragment.newInstance()
        )
        binding.run {
            viewPagerSearch.adapter = searchAdapter.apply {
                addFragment(listFragment)
            }
            tabLayout.setupWithViewPager(binding.viewPagerSearch)
        }
    }

    private fun handEvent() {
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchAdapter.setKeyword(it)
                }
                binding.searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })
    }

    private fun binding() {
        binding.lifecycleOwner = this
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}
