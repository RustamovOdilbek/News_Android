package com.innovation.news.presentation.main.search_screen

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.innovation.news.common.Constants
import com.innovation.news.common.UiStateList
import com.innovation.news.data.models.model.NewsModel
import com.innovation.news.databinding.FragmentSearchBinding
import com.innovation.news.presentation.adapter.NewsItewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SearchViewModel>()
    private val adapter by lazy { NewsItewAdapter(){data, position, clickType ->
        when(clickType){
            Constants.CLICK_SAVE -> {

            }

            Constants.CLICK_SHARE ->{

            }
        }
    } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        clickController()
    }

    private fun clickController() {
        binding.apply {
            etSearch.setOnEditorActionListener { textView, actionId, keyEvent ->
                if (actionId === EditorInfo.IME_ACTION_SEARCH || keyEvent != null && keyEvent.getAction() === KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() === KeyEvent.KEYCODE_ENTER
                ) {
                    performSearch() // Call your search logic method here
                    return@setOnEditorActionListener true
                }
                false
            }
        }
    }

    private fun performSearch() {
        val searchString = binding.etSearch.text.toString()
        viewModel.getSearchNewsList(searchString)
        initObserver()
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsState.collectLatest { state ->
                    when (state) {
                        UiStateList.EMPTY -> {}

                        UiStateList.LOADING -> {}

                        is UiStateList.SUCCESS -> {
                            Log.d("Dasdasdad", "initObserver: ${state.data}")
                            refreshAdapter(state.data)
                        }
                        is UiStateList.ERROR -> {
                            Log.d("Dasdasdad", "initObserver: ${state.message}")
                            //toast(msg = state.message)
                        }
                    }
                }
            }
        }
    }

    private fun refreshAdapter(newsList: List<NewsModel>) {
        adapter.submitList(newsList)
        binding.rvNews.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}