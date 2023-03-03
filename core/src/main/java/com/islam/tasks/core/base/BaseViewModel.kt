package com.islam.tasks.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.tasks.core.Action
import com.islam.tasks.core.Consumer
import com.islam.tasks.core.Producer
import com.islam.tasks.core.Reducer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE, EFFECT, EVENT>(
    private val dispatcher: CoroutineDispatcher,
    initialState: STATE
) : ViewModel() {

    protected abstract fun transform(event: EVENT)

    private val _uiState: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<EVENT> = MutableSharedFlow()
    private val event = _event.asSharedFlow()

    private val _effect: MutableSharedFlow<EFFECT> = MutableSharedFlow()
    val effect = _effect.asSharedFlow()

    // Get Current State
    protected val currentState: STATE
        get() = uiState.value

    init {
        viewModelScope.launch {
            event.collect {
                transform(it)
            }
        }
    }

    fun sendIntent(event: EVENT) {
        val newEvent = event
        viewModelScope.launch {
            _event.emit(newEvent)
        }
    }

    protected fun setState(reduce: Reducer<STATE>) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    protected fun setEffect(builder: Producer<EFFECT>) {
        val effectValue = builder()
        viewModelScope.launch { _effect.emit(effectValue) }
    }

    fun launchBlock(
        onStart: Action? = null,
        onError: Consumer<Throwable>? = null,
        block: suspend CoroutineScope.() -> Unit
    ) {
        onStart?.invoke()
        viewModelScope.launch(dispatcher + coroutineExceptionHandler(onError)) {
            block.invoke(this)
        }
    }

    private fun coroutineExceptionHandler(onError: Consumer<Throwable>?) =
        CoroutineExceptionHandler { _, throwable ->
            onError?.invoke(throwable)
        }
}
