package com.example.unsplash.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentImageDetailBinding

class ImageDetailFragment : Fragment() {

    private lateinit var binding: FragmentImageDetailBinding

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

    companion object {
        fun newInstance() = ImageDetailFragment()
    }
}
