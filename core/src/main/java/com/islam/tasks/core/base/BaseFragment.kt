package com.islam.tasks.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.islam.tasks.core.navigation.NavControllerManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


abstract class BaseFragment<binding : ViewBinding, NM : NavControllerManager, STATE, EFFECT, EVENT, VM : BaseViewModel<STATE, EFFECT, EVENT>> :
    Fragment() {

    protected abstract val viewModel: VM
    protected abstract val navControllerManager: NM

    private var _binding: binding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindView(inflater = inflater, container = container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navControllerManager.bind(NavHostFragment.findNavController(this))
        setup(savedInstanceState)
        observeOnUIUpdates()
        observeOnEffectsUpdates()
    }

    private fun observeOnUIUpdates() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->
                    renderState(uiState)
                }
            }
        }
    }

    private fun observeOnEffectsUpdates() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collectLatest {
                    renderEffects(it)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        navControllerManager.unbind()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract fun bindView(inflater: LayoutInflater, container: ViewGroup?): binding

    abstract fun renderState(ui: STATE)
    abstract fun renderEffects(effect: EFFECT)
    abstract fun setup(savedInstanceState: Bundle?)

}