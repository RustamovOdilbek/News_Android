package com.innovation.news.presentation.main.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.innovation.news.common.UiStateList
import com.innovation.news.common.UiStateObject
import com.innovation.news.data.models.model.NewsModel
import com.innovation.news.domain.use_case.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
): ViewModel() {

    private val _newsState = MutableStateFlow<UiStateList<NewsModel>>(UiStateList.EMPTY)
    val newsState get() = _newsState.asStateFlow()

    fun invoke(){
        viewModelScope.launch {
            newsUseCase()
            return@launch
        }
    }

    fun getAllNewsRemote() {
        _newsState.value = UiStateList.LOADING

        viewModelScope.launch {
            val result = newsUseCase.getAllNewsLocal()

            result.onSuccess { news ->
                _newsState.value = UiStateList.SUCCESS(news)
            }

            result.onFailure {
                _newsState.value =
                    UiStateList.ERROR(message = it.localizedMessage ?: "Something went wrong")
            }

            return@launch
        }
    }

    fun updateNewsLocal(newsModel: NewsModel){
        viewModelScope.launch {
            newsUseCase.updateNewsLocal(newsModel)
            return@launch
        }
    }

}