package com.islam.tasks.articles_list.presentation.model

data class ArticlesUiState(
    val showLoading: Boolean = false,
    val errorMessage: Int? = null,
    val emptyMessage: Int? = null,
    val articles: List<ArticleUiState> = mutableListOf())

data class ArticleUiState(val author: String?,
                          val title: String,
                          val urlToImage: String,
                          val publishedAt: String)