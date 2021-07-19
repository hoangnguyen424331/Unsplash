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
import com.example.unsplash.extentions.toGone
import com.example.unsplash.extentions.toVisible
import com.example.unsplash.utils.Status
import org.koin.android.ext.android.inject

class ImageDetailFragment : Fragment() {

    private lateinit var binding: FragmentImageDetailBinding
    private var isFavorite = false
    private var photoId: String? = null
    private var imageFavorite: ImageLocal? = null
    private val photoDetailViewModel: PhotoDetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photoId = it.getString(BUNDLE_PHOTO_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        registerObserver()
        handEvent()
    }

    private fun registerObserver() = with(photoDetailViewModel) {
        getPhotoDetail(photoId)
        favoriteImages(photoId)
        photoDetail.observe(viewLifecycleOwner, {
            when (it.status) {
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
                Status.LOADING -> {
                    binding.progressLayout.toVisible()
                }
            }
        })
        isFavorite.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data == true) {
                        binding.imageButtonFavorite.setImageResource(R.drawable.ic_favorite_checked)
                    } else {
                        binding.imageButtonFavorite.setImageResource(R.drawable.ic_favorite)
                    }
                }
                Status.ERROR -> {
                }
                Status.LOADING -> {
                }
            }
        })
    }

    private fun setData(photoDetail: PhotoDetail?) {
        photoDetail?.apply {
            id?.let {
                imageFavorite = ImageLocal(
                    it,
                    urlPhoto.thumb,
                    authorInformation.name,
                    authorInformation.avatar.smallAvatar,
                    views,
                    downloads,
                    likes
                )
            }
        }
    }

    private fun handEvent() {
        binding.imageButtonFavorite.setOnClickListener {
            if (!isFavorite) {
                imageFavorite?.let {
                    photoDetailViewModel.insertImages(it)
                }
            } else {
                imageFavorite?.let {
                    photoDetailViewModel.deleteImages(it)
                }
            }
        }
        binding.imageBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    companion object {
        const val BUNDLE_PHOTO_ID = "BUNDLE_PHOTO_ID"

        fun newInstance(id: String?) = ImageDetailFragment().apply {
            arguments = bundleOf(BUNDLE_PHOTO_ID to id)
        }
    }
}
