package com.islam.tasks.core

object ArticlesKeys {

    // Shared keys
    external fun geApiUrl(): String

    external fun geApiKey(): String

    init {
        System.loadLibrary("main")
    }
}
