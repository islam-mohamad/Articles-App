package com.islam.tasks.articles_list.data.model

import com.google.gson.annotations.SerializedName
import com.islam.tasks.articles_list.data.model.ArticleModel

data class ArticleResponseModel(
    @SerializedName("status") val status: String,
    @SerializedName("source") val source: String,
    @SerializedName("sortBy") val sortBy: String,
    @SerializedName("articles") val articles: List<ArticleModel>
)