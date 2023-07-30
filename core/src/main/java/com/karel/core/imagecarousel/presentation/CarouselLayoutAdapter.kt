package com.karel.core.imagecarousel.presentation

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.karel.core.databinding.LayoutCarouselItemBinding

class CarouselLayoutAdapter(private var dataSet: List<Uri>) :
    RecyclerView.Adapter<CarouselLayoutAdapter.ViewHolder>() {

    class ViewHolder(private val binding: LayoutCarouselItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setImage(uri: Uri) {
            //Glide.get(itemView.context).setMemoryCategory(MemoryCategory.LOW)
            Glide.with(itemView.context)
                .load(uri)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(binding.carouselImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            LayoutCarouselItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setImage(dataSet[position])
    }

    fun setItems(dataSet: List<Uri>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

}