package com.puzzling.puzzlingaos.presentation.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzling.puzzlingaos.data.model.response.ResponseMyRetroListDto
import com.puzzling.puzzlingaos.domain.repository.MyPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRetrospectViewModel @Inject constructor(private val repository: MyPageRepository) :
    ViewModel() {

    private val _responseReview = MutableLiveData<List<ResponseMyRetroListDto.ReviewData>?>()
    val responseReveiew: LiveData<List<ResponseMyRetroListDto.ReviewData>?> get() = _responseReview

    init {
        getMyProjectReview()
    }

    fun getMyProjectReview() = viewModelScope.launch {
        kotlin.runCatching {
            repository.getMyProjectReview(1, 11)
        }.onSuccess { response ->
            _responseReview.value = response.data
            Log.d("MyProjectRetro", "$response")
        }.onFailure {
            Log.d("MyProjectRetro", "$it")
        }
    }

}
