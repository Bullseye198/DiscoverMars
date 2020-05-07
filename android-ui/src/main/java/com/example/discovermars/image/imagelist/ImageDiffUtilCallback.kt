package com.example.discovermars.image.imagelist

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.image.model.Image

class ImageDiffUtilCallback: DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }
}