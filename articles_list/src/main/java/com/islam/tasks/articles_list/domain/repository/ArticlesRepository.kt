package com.islam.tasks.articles_list.domain.repository

import com.islam.tasks.articles_list.domain.entity.ArticleEntity

interface ArticlesRepository {
    suspend fun getArticles(source:String): List<ArticleEntity>
}