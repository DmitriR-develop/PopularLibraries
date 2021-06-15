package com.example.popularlibraries.model

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadIntro(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}