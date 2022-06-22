package com.example.ongsomosmas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ongsomosmas.Dto.News
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.FragmentNewsBinding
import com.example.ongsomosmas.databinding.ImageSliderItemBinding
import com.google.android.material.imageview.ShapeableImageView

class SliderViewAdapter(private var imageList: List<String>) :
    RecyclerView.Adapter<SliderViewAdapter.ImagesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderViewAdapter.ImagesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_slider_item,parent,false)
        return ImagesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewAdapter.ImagesViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int {
        return this.imageList.size
    }

    class ImagesViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

        private val sliderImageView = itemView.findViewById<ShapeableImageView>(R.id.sliderImageView)

        fun bind(data: String) {
            Glide.with(itemView.context)
                .load(data)
                .into(sliderImageView)
        }
    }
}