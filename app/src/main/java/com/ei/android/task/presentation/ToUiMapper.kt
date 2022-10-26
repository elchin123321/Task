package com.ei.android.task.presentation

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ToUiMapper(
    private val imageTV: ImageView,
    private val userTV: TextView,
    private val descriptionTV: TextView
) : PhotoUi.Mapper<Unit> {
    override fun map(description: String?, user: String, url: String) {
        Glide
            .with(imageTV.context)
            .load(url)
            .apply(RequestOptions().override(1000))
            .into(imageTV)
        userTV.text = user
        descriptionTV.text = description
    }
}