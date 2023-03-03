package com.islam.tasks.articles_list.presentation.model

sealed class ArticlesIntent {
    data class GetArticles(val source: String) : ArticlesIntent()
    data class NavigateToDetails(val deepLink: String) : ArticlesIntent()
}