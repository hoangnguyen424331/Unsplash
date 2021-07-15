package com.example.unsplash.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.unsplash.extentions.loadFromUrl
import com.example.unsplash.extentions.loadUrlStaggered
import com.example.unsplash.utils.LoadMoreRecyclerViewListener
import com.example.unsplash.utils.RefreshRecyclerViewListener

@BindingAdapter("onLoadImage")
fun ImageView.loadImage(url: String?) {
    url?.let {
        loadFromUrl(it)
    }
}

@BindingAdapter("onLoadImageStaggered")
fun ImageView.loadImageStaggered(url: String) {
    loadUrlStaggered(url)
}

@BindingAdapter(value = ["adapter"])
fun RecyclerView.setAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter
}

@BindingAdapter(value = ["isLoading", "onLoadMore"])
fun RecyclerView.onScrollListener(
    isLoading: Boolean,
    loadMoreRecyclerViewListener: LoadMoreRecyclerViewListener
) {
    clearOnScrollListeners()
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy > 0) {
                when (recyclerView.layoutManager) {
                    is LinearLayoutManager -> {
                        (recyclerView.layoutManager as LinearLayoutManager).run {
                            if (!isLoading && findLastCompletelyVisibleItemPosition() == itemCount - 1) {
                                loadMoreRecyclerViewListener.onLoadData()
                            }
                        }
                    }
                    is StaggeredGridLayoutManager -> {
                        (recyclerView.layoutManager as StaggeredGridLayoutManager).run {
                            val lastVisibleItemPosition =
                                getLastVisibleItem(findLastVisibleItemPositions(null))
                            if (!isLoading && (childCount + lastVisibleItemPosition) >= itemCount) {
                                loadMoreRecyclerViewListener.onLoadData()
                            }
                        }
                    }
                }
            }
        }
    })
}

@BindingAdapter(value = ["onRefresh"])
fun SwipeRefreshLayout.onRefresh(refresh: RefreshRecyclerViewListener) {
    setOnRefreshListener {
        refresh.onRefreshData()
    }
}

fun getLastVisibleItem(lastVisibleItemPosition: IntArray): Int {
    var maxSize = 0
    for (i in lastVisibleItemPosition.indices) {
        if (i == 0) {
            maxSize = lastVisibleItemPosition[i]
        } else if (lastVisibleItemPosition[i] > maxSize) {
            maxSize = lastVisibleItemPosition[i]
        }
    }
    return maxSize
}
