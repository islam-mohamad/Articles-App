package com.islam.tasks.articles_list.domain.usecase

import com.islam.tasks.articles_list.domain.entity.ArticleEntity
import com.islam.tasks.articles_list.domain.repository.ArticlesRepository
import com.islam.tasks.core.base.SuspendUseCase
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val repository: ArticlesRepository):
    SuspendUseCase<String, List<ArticleEntity>> {
    override suspend operator fun invoke(param: String): List<ArticleEntity> {
        return repository.getArticles(param)
    }
}