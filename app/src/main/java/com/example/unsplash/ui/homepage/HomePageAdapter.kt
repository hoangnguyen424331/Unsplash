@file:Suppress("DEPRECATION")

package com.example.unsplash.ui.homepage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

@Suppress("DEPRECATION")
class HomePageAdapter(
    fragmentManager: FragmentManager,
    private var fragments: List<Fragment>
) : FragmentPagerAdapter(fragmentManager) {
    override fun getCount() = fragments.size

    override fun getItem(position: Int)= fragments[position]
}
