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
import com.google.android.material.imageview.ShapeableImageView

class SliderViewAdapter(private val urlList: List<String>) :
    RecyclerView.Adapter<SliderViewAdapter.SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ImageSliderItemBinding.inflate(layoutInflater, parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(urlList[position])
    }

    override fun getItemCount(): Int {
        return this.urlList.size
    }

    class SliderViewHolder(private val binding: ImageSliderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val sliderImageView =
            itemView.findViewById<ShapeableImageView>(R.id.slidetImageView)
       // private val progressBar =
      //      itemView.findViewById<ShapeableImageView>(R.id.progressBar)
        fun bind(url: String) {
            Glide.with(itemView.context)
                .load(url)
                .fitCenter()
 /*               .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        println("cargo mal")
                        return true
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        println("cargo bien")
                       // progressBar.visibility = View.GONE
                        return true
                    }

                })*/
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(sliderImageView)
        }
    }

}

