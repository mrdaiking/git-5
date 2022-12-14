package com.demo.git5.utils.viewUtils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.demo.git5.R

fun ImageView.load(imgResource : Any?) {
    Glide.with(context.applicationContext)
        .load(imgResource)
        .circleCrop()
        .transition(DrawableTransitionOptions.withCrossFade(getDrawableFactory()))
        .placeholder(R.drawable.ic_user)
        .into(this)
}

private fun getDrawableFactory() : DrawableCrossFadeFactory {
    return DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
}