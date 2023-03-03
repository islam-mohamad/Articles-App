package com.islam.tasks.articles_list.di

import com.islam.tasks.articles_list.data.repository.ArticlesRepositoryImpl
import com.islam.tasks.articles_list.data.source.remote.ArticlesApi
import com.islam.tasks.articles_list.domain.repository.ArticlesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class ArticlesModule {
    @Binds
    abstract fun bindArticlesRepository(impl: ArticlesRepositoryImpl): ArticlesRepository

    companion object {
        @Provides
        fun provideUsersApi(retrofit: Retrofit): ArticlesApi = retrofit.create(ArticlesApi::class.java)
    }
}