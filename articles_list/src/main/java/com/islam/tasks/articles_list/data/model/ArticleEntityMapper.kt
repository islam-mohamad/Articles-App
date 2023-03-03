package com.islam.tasks.articles_list.data.model

import com.islam.tasks.articles_list.domain.entity.ArticleEntity

fun ArticleModel.toEntity() = ArticleEntity(
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt
)
