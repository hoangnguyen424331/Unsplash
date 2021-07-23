package com.example.unsplash.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentFavoriteBinding
import com.example.unsplash.ui.detail.ImageDetailFragment
import com.example.unsplash.ui.favorite.adapter.FavoriteAdapter
import com.example.unsplash.utils.Status
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by inject()
    private val favoriteAdapter by lazy {
        FavoriteAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFavorite()
        handEvent()
    }

    private fun initFavorite() = with(favoriteViewModel) {
        getImages()
        favoriteViewModel.favoriteImage.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    favoriteAdapter.submitList(it.data)
                    favoriteAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                }
                Status.LOADING -> {
                }
            }
        })
    }

    private fun handEvent() {
        favoriteAdapter.setOnClickItem {
            findNavController().navigate(
                R.id.imageDetailFragment,
                bundleOf(ImageDetailFragment.BUNDLE_PHOTO_ID to it.id)
            )
        }
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}
