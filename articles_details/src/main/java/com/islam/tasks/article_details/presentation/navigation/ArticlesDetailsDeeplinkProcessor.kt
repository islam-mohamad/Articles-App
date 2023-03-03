package com.islam.tasks.article_details.presentation.navigation

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.islam.tasks.core.navigation.DeeplinkProcessor
import com.islam.tasks.core.navigation.NavControllerManager
import javax.inject.Inject

class ArticlesDetailsDeeplinkProcessor @Inject constructor(
    private val navControllerManager: NavControllerManager
) : DeeplinkProcessor {

    override fun matches(deeplink: String): Boolean {
        return deeplink.contains("articledetails")
    }

    override fun execute(deeplink: String) {
        val request =
            NavDeepLinkRequest.Builder.fromUri(deeplink.toUri())
                .build()
        navControllerManager.navController?.navigate(request)
    }
}