package com.islam.tasks.core

typealias Action = () -> Unit
typealias Producer<T> = () -> T
typealias Reducer<T> = T.() -> T
typealias Consumer<T> = (T) -> Unit
