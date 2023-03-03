package com.islam.tasks.article_details.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.islam.tasks.core.navigation.NavControllerManager
import com.islam.tasks.posts.databinding.FragmentArticleDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticlesDetailsFragment : Fragment() {

    @Inject
    lateinit var navControllerManager : NavControllerManager

    private lateinit var binding: FragmentArticleDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navControllerManager.bind(NavHostFragment.findNavController(this))
        arguments?.run {
            val author = getString("author")
            Toast.makeText(requireContext(), "author name is $author", Toast.LENGTH_LONG).show()
        }
    }

    override fun onPause() {
        super.onPause()
        navControllerManager.unbind()
    }
}