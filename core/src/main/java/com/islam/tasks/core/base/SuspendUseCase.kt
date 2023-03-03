package com.islam.tasks.core.base

interface SuspendUseCase<PARAM, TYPE> {
    suspend operator fun invoke(param: PARAM): TYPE
}
