package com.islam.tasks.articles_list.presentation.model

import com.islam.tasks.articles_list.domain.entity.ArticleEntity

fun ArticleEntity.toUiState() = ArticleUiState(
    author = author ?: "",
    title = title,
    urlToImage = if (urlToImage.trim()
            .isEmpty()
    ) "https://www.nccpimandtip.gov.eg/uploads/newsImages/1549208279-default-news.png" else urlToImage,
    publishedAt = publishedAt
)