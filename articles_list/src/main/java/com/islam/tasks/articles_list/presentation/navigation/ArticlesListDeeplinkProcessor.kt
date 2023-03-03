package com.islam.tasks.articles_list.presentation.navigation

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import com.islam.tasks.core.navigation.DeeplinkProcessor
import com.islam.tasks.core.navigation.NavControllerManager
import javax.inject.Inject

class ArticlesListDeeplinkProcessor @Inject constructor(
    private val navControllerManager: NavControllerManager
) : DeeplinkProcessor {

    override fun matches(deeplink: String): Boolean {
        return deeplink.contains("/articleslist")
    }

    override fun execute(deeplink: String) {
        val request =
            NavDeepLinkRequest.Builder.fromUri(deeplink.toUri())
                .build()
        navControllerManager.navController?.navigate(request)
    }
}