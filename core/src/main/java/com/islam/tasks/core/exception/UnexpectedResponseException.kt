package com.islam.tasks.core.exception

data class UnexpectedResponseException(val msg: String?) : Throwable("UnexpectedResponseException ${msg?:""}")
