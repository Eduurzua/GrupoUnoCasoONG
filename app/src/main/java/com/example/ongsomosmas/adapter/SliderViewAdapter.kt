package com.example.ongsomosmas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.ImageSliderItemBinding
import com.google.android.material.imageview.ShapeableImageView

class SliderViewAdapter(private val urlList : List<String>) : RecyclerView.Adapter<SliderViewAdapter.SliderViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ImageSliderItemBinding.inflate(layoutInflater,parent,false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(urlList[position])
    }

    override fun getItemCount(): Int {
        return this.urlList.size
    }

    class SliderViewHolder( private val binding: ImageSliderItemBinding): RecyclerView.ViewHolder(binding.root) {
        private val sliderImageView = itemView.findViewById<ShapeableImageView>(R.id.slidetImageView)
        fun bind(url: String) {
            Glide.with(itemView.context)
                .load(url)
                .fitCenter()
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(sliderImageView)
            }
        }
}