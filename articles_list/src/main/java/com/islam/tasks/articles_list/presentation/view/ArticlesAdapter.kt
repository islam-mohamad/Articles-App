package com.islam.tasks.articles_list.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.islam.tasks.articles_list.presentation.model.ArticleUiState
import com.islam.tasks.users.databinding.ItemUserBinding
import com.squareup.picasso.Picasso

class ArticlesAdapter(private val itemClick: (ArticleUiState) -> Unit) :
    ListAdapter<ArticleUiState, ArticlesAdapter.ArticleViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { itemClick(item) }
    }

    class ArticleViewHolder(private val binding: ItemUserBinding) : ViewHolder(binding.root) {
        fun bind(item: ArticleUiState) = with(binding) {
            titleTv.text = item.title
            authorTv.text = item.author
            timeTv.text = item.publishedAt
            Picasso.get().load(item.urlToImage).fit().into(articleIv)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ArticleUiState>() {
        override fun areItemsTheSame(oldItem: ArticleUiState, newItem: ArticleUiState): Boolean {
            return oldItem.author == newItem.author
                    && oldItem.title == newItem.title
                    && oldItem.urlToImage == newItem.urlToImage
                    && oldItem.publishedAt == newItem.publishedAt
        }

        override fun areContentsTheSame(
            oldItem: ArticleUiState, newItem: ArticleUiState
        ): Boolean {
            return oldItem == newItem
        }
    }
}