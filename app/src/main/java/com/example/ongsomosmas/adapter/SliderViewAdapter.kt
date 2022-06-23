package com.example.ongsomosmas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ongsomosmas.Dto.News
import com.example.ongsomosmas.databinding.ImageSliderItemBinding

class SliderViewAdapter() : RecyclerView.Adapter<SliderViewAdapter.SliderViewHolder>(){

    private lateinit var news: List<News>

    fun setNews(newANews : List<News>) {
        this.news = newANews
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ImageSliderItemBinding.inflate(layoutInflater,parent,false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(this.news[position])
    }

    override fun getItemCount(): Int {
        return this.news.size
    }

    class SliderViewHolder( private val binding: ImageSliderItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
        //TODO
            }
        }
}