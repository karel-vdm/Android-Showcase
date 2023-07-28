package com.karel.core.presentation.image_carousel

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.google.android.material.carousel.CarouselLayoutManager
import com.karel.core.databinding.LayoutCarouselListBinding
import com.karel.core.presentation.image_carousel.model.ImageCarouselModel

class ImageCarousel @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding = LayoutCarouselListBinding
        .inflate(LayoutInflater.from(context), this, true)
    private val carouselLayoutAdapter = CarouselLayoutAdapter(emptyList())

    init {
        binding.carouselList.layoutManager = CarouselLayoutManager()
        binding.carouselList.adapter = carouselLayoutAdapter
    }

    fun renderWithModel(imageCarouselModel: ImageCarouselModel) {
        binding.carouselTitle.text = imageCarouselModel.title
        if (!imageCarouselModel.isEmpty) {
            binding.emptyStateText.isVisible = false
            carouselLayoutAdapter.setItems(imageCarouselModel.imageUriList)
        } else {
            binding.carouselList.isVisible = false
            binding.emptyStateText.isVisible = true
            binding.emptyStateText.text = imageCarouselModel.emptyStateText
        }
    }

}