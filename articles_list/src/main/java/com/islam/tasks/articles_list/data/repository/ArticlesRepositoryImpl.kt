package com.islam.tasks.articles_list.data.repository

import com.islam.tasks.articles_list.data.model.toEntity
import com.islam.tasks.articles_list.data.source.remote.ArticlesApi
import com.islam.tasks.articles_list.domain.entity.ArticleEntity
import com.islam.tasks.articles_list.domain.repository.ArticlesRepository
import com.islam.tasks.core.di.IODispatcher
import com.islam.tasks.core.base.BaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val api: ArticlesApi,
    @IODispatcher dispatcher: CoroutineDispatcher,
) : ArticlesRepository, BaseRepository(dispatcher) {
    override suspend fun getArticles(source: String): List<ArticleEntity> = runOnIO {
        api.getArticlesAsync(source).articles.map { it.toEntity() }
    }
}