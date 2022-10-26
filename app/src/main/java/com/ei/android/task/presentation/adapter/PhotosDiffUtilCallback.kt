package com.ei.android.task.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ei.android.task.presentation.PhotoUi

class PhotosDiffUtilCallback : DiffUtil.ItemCallback<PhotoUi>() {
    override fun areItemsTheSame(oldItem: PhotoUi, newItem: PhotoUi): Boolean {
        return oldItem.sameItem(newItem)
    }

    override fun areContentsTheSame(oldItem: PhotoUi, newItem: PhotoUi): Boolean {
        return oldItem == newItem
    }

}