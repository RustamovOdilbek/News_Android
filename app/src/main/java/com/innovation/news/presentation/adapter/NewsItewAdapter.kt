package com.innovation.news.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.innovation.news.common.Constants.TYPE_ITEM_LARGE
import com.innovation.news.common.Constants.TYPE_ITEM_SMALL
import com.innovation.news.data.models.model.NewsModel
import com.innovation.news.databinding.ItemLargeNewsViewBinding
import com.innovation.news.databinding.ItemSmallNewsViewBinding
import com.squareup.picasso.Picasso

class NewsItewAdapter: ListAdapter<NewsModel, ViewHolder>(DiffUtil()) {

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<NewsModel>() {
        override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {

        return when (position % 4) {
            0 -> TYPE_ITEM_LARGE
            else -> TYPE_ITEM_SMALL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return when (viewType) {
            TYPE_ITEM_LARGE ->
                LargeItemViewHolder( ItemLargeNewsViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

            TYPE_ITEM_SMALL ->
                SmallItemViewHolder(ItemSmallNewsViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

            else -> SmallItemViewHolder(ItemSmallNewsViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is LargeItemViewHolder -> holder.bind(item)
            is SmallItemViewHolder -> holder.bind(item)
            else -> throw IllegalArgumentException("Invalid view holder type")
        }
    }

    inner class LargeItemViewHolder(val binding: ItemLargeNewsViewBinding) : ViewHolder(binding.root) {
        fun bind(item: NewsModel) {
            binding.apply {
                Picasso.get().load(item.urlToImage).into(ivNewsImage)
                tvNewsBody.text = item.title
            }
        }
    }

    inner class SmallItemViewHolder(val binding: ItemSmallNewsViewBinding) : ViewHolder(binding.root) {
        fun bind(item: NewsModel) {
            binding.apply {
                binding.apply {
                    Picasso.get().load(item.urlToImage).into(ivNewsImage)
                    tvNewsBody.text = item.title
                    tvNewsTitle.text = item.author
                }
            }
        }
    }
}