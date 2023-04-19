package com.samm.ktor01.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samm.ktor01.data.Repository
import com.samm.ktor01.domain.Apod
import com.samm.ktor01.domain.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AstroViewModel: ViewModel() {

    val responseFlow_ = MutableStateFlow<Response?>(null)
    val responseFlow: StateFlow<Response?> = responseFlow_

    val responseFlowList_ = MutableStateFlow<List<Apod?>>(emptyList())
    val responseFlowList: StateFlow<List<Apod?>> = responseFlowList_

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                Repository.getDataList(3)
            }.onSuccess {
                responseFlowList_.value = it
            }.onFailure { throwable ->
                responseFlowList_.value = emptyList()
                throwable.message?.let { it -> Log.d("Error:", it) }
            }
        }
    }
}