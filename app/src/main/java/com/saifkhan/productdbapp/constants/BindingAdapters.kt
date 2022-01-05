package com.saifkhan.productdbapp.constants

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.saifkhan.productdbapp.R

object BindingAdapters {


}

@BindingAdapter("agentImageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .apply(RequestOptions().error(R.drawable.ic_baseline_shopping_cart_24))
        .into(view)
}
