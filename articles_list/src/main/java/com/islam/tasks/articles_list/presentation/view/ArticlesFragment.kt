package com.islam.tasks.articles_list.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.islam.tasks.articles_list.presentation.model.ArticlesEffects
import com.islam.tasks.articles_list.presentation.model.ArticlesIntent
import com.islam.tasks.articles_list.presentation.model.ArticlesUiState
import com.islam.tasks.articles_list.presentation.viewmodel.ArticlesViewModel
import com.islam.tasks.core.base.BaseFragment
import com.islam.tasks.core.navigation.DefaultDeeplinkHandler
import com.islam.tasks.core.navigation.NavControllerManager
import com.islam.tasks.core.visible
import com.islam.tasks.users.databinding.FragmentArticlesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ArticlesFragment :
    BaseFragment<FragmentArticlesBinding, NavControllerManager, ArticlesUiState, ArticlesEffects, ArticlesIntent, ArticlesViewModel>() {

    override val viewModel by viewModels<ArticlesViewModel>()

    @Inject
    override lateinit var navControllerManager: NavControllerManager

    @Inject
    lateinit var defaultDeeplinkHandler: DefaultDeeplinkHandler
    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter {
            viewModel.sendIntent(ArticlesIntent.NavigateToDetails("articledetails://articles?author=${it.author}"))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.sendIntent(ArticlesIntent.GetArticles("associated-press"))
    }

    override fun bindView(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentArticlesBinding = FragmentArticlesBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        initViews()
    }

    private fun initViews() {
        binding.userListRv.adapter = adapter
    }

    override fun renderState(ui: ArticlesUiState) = with(ui) {
        adapter.submitList(articles)
        binding.loadingPb.visible(showLoading)
    }

    override fun renderEffects(effect: ArticlesEffects) {

    }
}