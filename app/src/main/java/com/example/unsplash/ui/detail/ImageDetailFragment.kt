package com.example.unsplash.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.unsplash.R
import com.example.unsplash.data.model.PhotoDetail
import com.example.unsplash.data.source.local.sqlite.entity.ImageLocal
import com.example.unsplash.databinding.FragmentImageDetailBinding
import com.example.unsplash.extentions.hideStatusBar
import com.example.unsplash.extentions.toGone
import com.example.unsplash.extentions.toVisible
import com.example.unsplash.utils.Status
import org.koin.android.ext.android.inject

class ImageDetailFragment : Fragment() {

    private var isFavorite = false
    private var photoID: String? = null
    private var imageFavorite: ImageLocal? = null
    private lateinit var binding: FragmentImageDetailBinding
    private val imageDetailViewModel: ImageDetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photoID = it.getString(BUNDLE_PHOTO_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().hideStatusBar()
        binding = DataBindingUtil.inflate<FragmentImageDetailBinding>(
            inflater,
            R.layout.fragment_image_detail,
            container,
            false
        ).apply {
            lifecycleOwner = this@ImageDetailFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        handleEvent()
    }

    private fun handleEvent() {
        binding.imageButtonFavorite.setOnClickListener {
            if (!isFavorite) {
                imageFavorite?.let { imageLocalData ->
                    imageDetailViewModel.insertImage(imageLocalData)
                }
            } else {
                imageFavorite?.let { imageLocalData ->
                    imageDetailViewModel.deleteImage(imageLocalData)
                }
            }
        }
        binding.imageViewBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupObserver() = with(imageDetailViewModel) {
        getPhotoDetail(photoID)
        alreadyFavorite(photoID)
        photoDetailLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressLayout.toVisible()
                }
                Status.SUCCESS -> {
                    it.data.run {
                        binding.photoDetail = this
                        setData(this)
                    }
                    binding.progressLayout.toGone()
                }
                Status.ERROR -> {
                    binding.progressLayout.toGone()
                }
            }
        })
        isFavorite.observe(viewLifecycleOwner, {
            it.data?.let { isFavoriteData ->
                this@ImageDetailFragment.isFavorite = isFavoriteData
            }
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    if (it.data == true) {
                        binding.imageButtonFavorite.setImageResource(R.drawable.ic_favorite_checked)
                    } else {
                        binding.imageButtonFavorite.setImageResource(R.drawable.ic_favorite)
                    }
                }
                Status.ERROR -> {
                }
            }
        })
    }

    private fun setData(data: PhotoDetail?) {
        data?.apply {
            id?.let {
                imageFavorite = ImageLocal(
                    it,
                    urlPhoto.thumb,
                    authorInformation.name,
                    authorInformation.avatar.smallAvatar,
                    views,
                    likes,
                    downloads
                )
            }
        }
    }

    companion object {
        const val BUNDLE_PHOTO_ID = "BUNDLE_PHOTO_ID"

        fun newInstance(id: String?) = ImageDetailFragment().apply {
            arguments = bundleOf(BUNDLE_PHOTO_ID to id)
        }
    }
}
