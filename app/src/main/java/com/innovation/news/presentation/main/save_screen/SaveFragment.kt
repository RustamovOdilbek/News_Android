package com.innovation.news.presentation.main.save_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.innovation.news.R
import com.innovation.news.common.Constants
import com.innovation.news.common.UiStateList
import com.innovation.news.data.models.model.NewsModel
import com.innovation.news.databinding.FragmentSaveBinding
import com.innovation.news.presentation.adapter.NewsItewAdapter
import com.innovation.news.presentation.main.home_screen.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SaveFragment : Fragment() {
    private var _binding: FragmentSaveBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SaveViewModel>()
    private val adapter by lazy { NewsItewAdapter(){data, position, clickType ->
        when(clickType){
            Constants.CLICK_SAVE -> {
                viewModel.updateNewsLocal(data)
                viewModel.getSavedNewsList()
            }

            Constants.CLICK_SHARE ->{

            }
        }
    } }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getSavedNewsList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSaveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObserver()
    }

    private fun initViews() {
        clickController()
    }

    private fun clickController() {

    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsState.collectLatest { state ->
                    when (state) {
                        UiStateList.EMPTY -> {}

                        UiStateList.LOADING -> {}

                        is UiStateList.SUCCESS -> {
                            refreshAdapter(state.data)
                        }
                        is UiStateList.ERROR -> {
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