package com.islam.tasks.core.base

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<PARAM, TYPE> {
    operator fun invoke(param: PARAM): Flow<TYPE>
}
