package com.islam.tasks.core.navigation

interface DeeplinkHandler {
    fun process(deeplink: String): Boolean
}