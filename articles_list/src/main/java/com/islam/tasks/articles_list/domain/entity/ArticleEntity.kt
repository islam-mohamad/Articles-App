package com.islam.tasks.articles_list.domain.entity

data class ArticleEntity(
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String
)