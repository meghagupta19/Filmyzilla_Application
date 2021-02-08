package com.example.affirmations


import android.content.Context
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer


class ImageLoader {

    private val defaultImage: Int = R.drawable.ic_search

    private var mContext: Context? = null

    fun UniversalImageLoader(mContext: Context?) {
        this.mContext = mContext
    }

    fun getConfig(): ImageLoaderConfiguration? {
        // Universal image loader setup
        val defaultOptions = DisplayImageOptions.Builder()
            .showImageOnLoading(defaultImage)
            .showImageForEmptyUri(defaultImage)
            .showImageOnFail(defaultImage)
            .cacheOnDisk(true).cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(true)
            .imageScaleType(ImageScaleType.EXACTLY)
            .displayer(FadeInBitmapDisplayer(300)).build()
        return ImageLoaderConfiguration.Builder(mContext)
            .defaultDisplayImageOptions(defaultOptions)
            .memoryCache(WeakMemoryCache())
            .diskCacheSize(100 * 1024 * 1024)
            .build()
    }
}