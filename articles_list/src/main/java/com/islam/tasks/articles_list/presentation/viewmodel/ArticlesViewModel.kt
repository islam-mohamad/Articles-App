package com.islam.tasks.articles_list.presentation.viewmodel

import com.islam.tasks.articles_list.domain.usecase.GetArticlesUseCase
import com.islam.tasks.articles_list.presentation.model.ArticlesEffects
import com.islam.tasks.articles_list.presentation.model.ArticlesIntent
import com.islam.tasks.articles_list.presentation.model.ArticlesUiState
import com.islam.tasks.articles_list.presentation.model.toUiState
import com.islam.tasks.core.base.BaseViewModel
import com.islam.tasks.core.di.MainDispatcher
import com.islam.tasks.core.navigation.DefaultDeeplinkHandler
import com.islam.tasks.users.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


@HiltViewModel
class ArticlesViewModel @Inject constructor(
    @MainDispatcher val mainDispatcher: CoroutineDispatcher,
    private val getArticlesUseCase: GetArticlesUseCase,
    private val deeplinkHandler: DefaultDeeplinkHandler
) : BaseViewModel<ArticlesUiState, ArticlesEffects, ArticlesIntent>(
    mainDispatcher, ArticlesUiState()
) {

    private fun getArticles(source: String) =
        launchBlock(onStart = ::onGetArticlesStart, onError = {
            it.handleErrorState()
        }) {
            val articlesList = getArticlesUseCase(source).map { it.toUiState() }
            setState { copy(showLoading = false, articles = articlesList) }
        }

    private fun onGetArticlesStart() = setState {
        copy(showLoading = true)
    }

    override fun transform(event: ArticlesIntent): Unit = with(event) {
        when (this) {
            is ArticlesIntent.GetArticles -> {
                getArticles(source)
            }
            is ArticlesIntent.NavigateToDetails -> {
                deeplinkHandler.process(deepLink)
            }
        }
    }

    private fun Throwable.handleErrorState() {
        setState {
            copy(showLoading = false, errorMessage = R.string.something_wrong)
        }
    }
}