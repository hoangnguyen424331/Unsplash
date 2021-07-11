package com.example.unsplash.ui.photo_collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.unsplash.databinding.FragmentPhotoCollectionBinding

class PhotoCollectionFragment : Fragment() {

    lateinit var binding: FragmentPhotoCollectionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = PhotoCollectionFragment()
    }
}
