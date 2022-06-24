package com.example.ongsomosmas.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.ongsomosmas.R
import com.bumptech.glide.request.target.Target
import com.example.ongsomosmas.databinding.FragmentNewsBinding
import com.example.ongsomosmas.databinding.ImageSliderItemBinding
import com.example.ongsomosmas.databinding.ImageSliderItemMembersBinding
import com.google.android.material.imageview.ShapeableImageView

class SliderViewAdapterMembers(private val urlListMembers: List<String>) :
    RecyclerView.Adapter<SliderViewAdapterMembers.SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ImageSliderItemMembersBinding.inflate(layoutInflater, parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(urlListMembers[position])
    }

    override fun getItemCount(): Int {
        return this.urlListMembers.size
    }

    class SliderViewHolder(private val binding: ImageSliderItemMembersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val sliderImageView =
            itemView.findViewById<ShapeableImageView>(R.id.slidetImageViewMembers)
        fun bind(url: String) {
            Glide.with(itemView.context)
                .load(url)
                .fitCenter()
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(sliderImageView)
        }
    }
}

