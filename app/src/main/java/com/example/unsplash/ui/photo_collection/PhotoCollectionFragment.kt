package com.example.unsplash.ui.photo_collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.unsplash.R
import com.example.unsplash.data.model.Collection
import com.example.unsplash.data.model.Topic
import com.example.unsplash.databinding.FragmentPhotoCollectionBinding
import com.example.unsplash.ui.detail.ImageDetailFragment
import com.example.unsplash.ui.photo_collection.adapter.PhotoCollectionAdapter
import com.example.unsplash.utils.Constant
import com.example.unsplash.utils.Status
import org.koin.android.ext.android.inject

class PhotoCollectionFragment : Fragment() {

    lateinit var binding: FragmentPhotoCollectionBinding
    private val photoCollectionsViewModel: PhotoCollectionsViewModel by inject()
    private val photoCollectionAdapter by lazy {
        PhotoCollectionAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding()
        handleEvent()
        registerObserver()
    }

    override fun onStart() {
        super.onStart()
        arguments?.getParcelable<Collection>(BUNDLE_COLLECTION)?.let {
            photoCollectionsViewModel.fetchCollections(it.id, Constant.API_COLLECTION)
            setTitle(Constant.API_COLLECTION, it.title)
        } ?: arguments?.getParcelable<Topic>(BUNDLE_TOPIC)?.let {
            photoCollectionsViewModel.fetchCollections(it.id, Constant.API_TOPIC)
            setTitle(Constant.API_TOPIC, it.title)
        }
    }

    private fun setTitle(apiName: String, title: String) {
        binding.apply {
            textViewTitleCollection.text = title
            textTitle.text = apiName
        }
    }

    private fun binding() {
        binding.apply {
            lifecycleOwner = this@PhotoCollectionFragment
            viewModel = photoCollectionsViewModel
            (recyclerViewPhotoCollections.layoutManager as StaggeredGridLayoutManager).gapStrategy =
                StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            adapter = photoCollectionAdapter
        }
    }

    private fun handleEvent() {
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
        photoCollectionAdapter.setOnClickPhotoCollection {
            findNavController().navigate(
                R.id.imageDetailFragment,
                bundleOf(ImageDetailFragment.BUNDLE_PHOTO_ID to it.id)
            )
        }
    }

    private fun registerObserver() {
        photoCollectionsViewModel.resource.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    photoCollectionAdapter.submitList(it.data?.value?.toMutableList())
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

    companion object {
        const val BUNDLE_TOPIC = "BUNDLE_TOPIC"
        const val BUNDLE_COLLECTION = "BUNDLE_COLLECTION"
    }
}
