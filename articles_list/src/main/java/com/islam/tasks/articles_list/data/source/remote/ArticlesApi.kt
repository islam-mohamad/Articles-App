package com.islam.tasks.articles_list.data.source.remote

import com.islam.tasks.articles_list.data.model.ArticleResponseModel
import com.islam.tasks.core.ArticlesKeys
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApi {
    @GET("articles")
    suspend fun getArticlesAsync(@Query("source") source: String,
                                 @Query("apiKey") apiKey: String = ArticlesKeys.geApiKey()): ArticleResponseModel
}