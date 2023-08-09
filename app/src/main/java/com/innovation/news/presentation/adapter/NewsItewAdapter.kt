package com.innovation.news.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.innovation.news.R
import com.innovation.news.common.Constants.CLICK_SAVE
import com.innovation.news.common.Constants.CLICK_SHARE
import com.innovation.news.common.Constants.TYPE_ITEM_LARGE
import com.innovation.news.common.Constants.TYPE_ITEM_SMALL
import com.innovation.news.data.models.model.NewsModel
import com.innovation.news.databinding.ItemLargeNewsViewBinding
import com.innovation.news.databinding.ItemSmallNewsViewBinding
import com.squareup.picasso.Picasso

class NewsItewAdapter(private val onClick: (data: NewsModel, position: Int, clickType: Int) -> Unit) :
    ListAdapter<NewsModel, ViewHolder>(DiffUtil()) {

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
                LargeItemViewHolder(
                    ItemLargeNewsViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            TYPE_ITEM_SMALL ->
                SmallItemViewHolder(
                    ItemSmallNewsViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            else -> SmallItemViewHolder(
                ItemSmallNewsViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
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

    inner class LargeItemViewHolder(val binding: ItemLargeNewsViewBinding) :
        ViewHolder(binding.root) {
        fun bind(item: NewsModel) {
            binding.apply {
                Picasso.get().load(item.urlToImage).into(ivNewsImage)
                tvNewsBody.text = item.title

                ivSave.setImageResource(if(item.isSaved)  R.drawable.ic_nav_saved else R.drawable.ic_nav_save)

                ivSave.setOnClickListener {
                    item.isSaved = !item.isSaved
                    ivSave.setImageResource(if(item.isSaved)  R.drawable.ic_nav_saved else R.drawable.ic_nav_save)
                    onClick(item, position, CLICK_SAVE)
                }

                ivShare.setOnClickListener {
                    onClick(item, position, CLICK_SHARE)
                }

                if (position == itemCount - 1){
                    layout.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        bottomMargin = 160
                    }
                }
            }
        }
    }

    inner class SmallItemViewHolder(val binding: ItemSmallNewsViewBinding) :
        ViewHolder(binding.root) {
        fun bind(item: NewsModel) {
            binding.apply {
                Picasso.get().load(item.urlToImage).into(ivNewsImage)
                tvNewsBody.text = item.title
                tvNewsTitle.text = item.author

                ivSave.setImageResource(if(item.isSaved)  R.drawable.ic_nav_saved else R.drawable.ic_nav_save)

                ivSave.setOnClickListener {
                    item.isSaved = !item.isSaved
                    ivSave.setImageResource(if(item.isSaved)  R.drawable.ic_nav_saved else R.drawable.ic_nav_save)
                    onClick(item, position, CLICK_SAVE)
                }

                ivShare.setOnClickListener {
                    onClick(item, position, CLICK_SHARE)
                }

                if (position == itemCount - 1){
                    layout.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        bottomMargin = 160
                    }
                }
            }
        }
    }

}