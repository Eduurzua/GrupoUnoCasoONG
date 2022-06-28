package com.example.ongsomosmas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.ImageSliderItemMembersBinding

class RecyclerViewAdapterMembers(
    private val urlListMembers: List<String>,
    private val listener: MemberSelectionListener
) :
    RecyclerView.Adapter<RecyclerViewAdapterMembers.SliderViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ImageSliderItemMembersBinding.inflate(layoutInflater, parent, false)
        return SliderViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(urlListMembers[position])
    }

    interface MemberSelectionListener {
        fun select(position: Int)
    }

    override fun getItemCount(): Int {
        return this.urlListMembers.size
    }

    class SliderViewHolder(
        private val binding: ImageSliderItemMembersBinding,
        private val listener: MemberSelectionListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private val sliderImageView =
            binding.iViewRecycler

        fun bind(url: String) {
            Glide.with(itemView.context)
                .load(url)
                .fitCenter()
                .override(500,300)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(sliderImageView)

            this.binding.iViewRecycler.setOnClickListener {
                listener.select(adapterPosition)
            }
        }
    }
}

