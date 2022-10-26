package com.ei.android.task.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.ei.android.task.databinding.ItemPhotoBinding
import com.ei.android.task.presentation.PhotoUi

class PhotosAdapter(
    private val clickListener: OnImageClickListener
) : PagingDataAdapter<PhotoUi, PhotosViewHolder>(PhotosDiffUtilCallback()) {

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {

        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PhotosViewHolder(binding, clickListener)
    }

    interface OnImageClickListener {
        fun onClickListener(url: String)
    }
}