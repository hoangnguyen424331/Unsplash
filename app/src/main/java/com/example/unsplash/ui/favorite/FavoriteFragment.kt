package com.example.unsplash.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.unsplash.databinding.FragmentFavoriteBinding
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
    }

    private fun initFavorite()= with(favoriteViewModel) {
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

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}
