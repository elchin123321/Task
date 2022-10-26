package com.ei.android.task.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ei.android.task.databinding.ItemPhotoBinding
import com.ei.android.task.presentation.PhotoUi
import com.ei.android.task.presentation.ToUiMapper

class PhotosViewHolder(
    private val binding: ItemPhotoBinding,
    private val clickListener: PhotosAdapter.OnImageClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    private val mapper = ToUiMapper(binding.image, binding.userName, binding.imageDescription)
    fun bind(data: PhotoUi) {
        data.map(mapper)
        binding.image.setOnClickListener {
            data.openPhoto(clickListener)
        }
    }
}