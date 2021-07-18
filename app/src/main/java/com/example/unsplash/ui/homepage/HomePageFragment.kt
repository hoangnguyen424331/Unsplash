package com.example.unsplash.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentHomePageBinding
import com.example.unsplash.ui.collection.CollectionFragment
import com.example.unsplash.ui.favorite.FavoriteFragment
import com.example.unsplash.ui.home.HomeFragment
import com.example.unsplash.utils.ItemBottomNav

@Suppress("DEPRECATION")
class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitView()
        onInitNav()
        onEvent()
    }

    private fun onEvent() {}

    private fun onInitNav() {
        binding.bottomNavHomePge.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.itemHomePage -> {
                    setTitle(R.string.home)
                    setViewPager(ItemBottomNav.HOME_ITEM.position)
                }
                R.id.itemCollectionPage -> {
                    setTitle(R.string.collection)
                    setViewPager(ItemBottomNav.COLLECTION_ITEM.position)
                }
                R.id.itemFavoritePage -> {
                    setTitle(R.string.favorite)
                    setViewPager(ItemBottomNav.FAVORITE_ITEM.position)
                }
                else -> false
            }
        }
    }

    private fun onInitView() {
        val listFragment = listOf(
            HomeFragment.newInstance(),
            CollectionFragment.newInstance(),
            FavoriteFragment.newInstance()
        )
        childFragmentManager.let {
            binding.viewPagerHomePage.adapter = HomePageAdapter(it, listFragment)
        }
        binding.viewPagerHomePage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                binding.bottomNavHomePge.menu.getItem(position).isChecked = true
                when (position) {
                    ItemBottomNav.HOME_ITEM.ordinal -> setTitle(R.string.home)
                    ItemBottomNav.COLLECTION_ITEM.ordinal -> setTitle(R.string.collection)
                    ItemBottomNav.FAVORITE_ITEM.ordinal -> setTitle(R.string.favorite)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun setTitle(stringTitle: Int) {
        binding.textTitle.text = getString(stringTitle)
    }

    private fun setViewPager(position: Int): Boolean {
        binding.viewPagerHomePage.currentItem = position
        return true
    }
}