package com.karel.core.imagecarousel.presentation.model

import android.net.Uri

data class ImageCarouselModel(
    val title: String = String(),
    val imageUriList: List<Uri> = emptyList(),
    val emptyStateText: String = String(),
) {
    val isEmpty: Boolean
        get() {
            return imageUriList.isEmpty()
        }
}