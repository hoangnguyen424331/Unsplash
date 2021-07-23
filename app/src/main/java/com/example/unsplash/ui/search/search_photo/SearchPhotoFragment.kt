package com.example.unsplash.ui.search.search_photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentSearchPhotoBinding
import com.example.unsplash.ui.detail.ImageDetailFragment.Companion.BUNDLE_PHOTO_ID
import com.example.unsplash.ui.photo_collection.adapter.PhotoCollectionAdapter
import com.example.unsplash.utils.Status
import org.koin.android.ext.android.inject

class SearchPhotoFragment : Fragment() {

    lateinit var binding: FragmentSearchPhotoBinding
    private val searchPhotoViewModel: SearchPhotoViewModel by inject()
    private val photoAdapter by lazy {
        PhotoCollectionAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding()
        handEvent()
        registerObserver()
    }

    private fun binding() {
        binding.apply {
            lifecycleOwner = this@SearchPhotoFragment
            viewModel = searchPhotoViewModel
            (recyclerViewPhoto.layoutManager as StaggeredGridLayoutManager).gapStrategy =
                StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            adapter = photoAdapter
        }
    }

    private fun handEvent() {
        photoAdapter.setOnClickPhotoCollection {
            findNavController().navigate(
                R.id.imageDetailFragment,
                bundleOf(BUNDLE_PHOTO_ID to it.id)
            )
        }
    }

    private fun registerObserver() {
        searchPhotoViewModel.resource.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    photoAdapter.submitList(it.data?.value?.toMutableList())
                    binding.swipeRefresh.isRefreshing = false
                }
                Status.ERROR -> {
                    binding.swipeRefresh.isRefreshing = false
                }
                Status.LOADING -> {
                }
            }
        })
    }

    fun searchPhoto(keyWord: String) {
        searchPhotoViewModel.searchFirstTime(keyWord)
    }

    companion object {
        fun newInstance() = SearchPhotoFragment()
    }
}
