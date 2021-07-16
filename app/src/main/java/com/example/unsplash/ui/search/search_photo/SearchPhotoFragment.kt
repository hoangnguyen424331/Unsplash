package com.example.unsplash.ui.search.search_photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.unsplash.databinding.FragmentSearchPhotoBinding

class SearchPhotoFragment : Fragment() {

    lateinit var binding: FragmentSearchPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun searchPhoto(keyWord: String) {}

    companion object {
        fun newInstance() = SearchPhotoFragment()
    }
}
