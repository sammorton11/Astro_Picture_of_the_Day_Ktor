package com.samm.ktor01.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samm.ktor01.data.Repository
import com.samm.ktor01.domain.Apod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class AstroViewModel: ViewModel() {

    val responseFlow_ = MutableStateFlow<Apod?>(null)
    val responseFlow: StateFlow<Apod?> = responseFlow_

    val responseFlowList_ = MutableStateFlow<List<Apod?>>(emptyList())
    val responseFlowList: StateFlow<List<Apod?>> = responseFlowList_

    val responseByFlowList_ = MutableStateFlow<Apod?>(null)
    val responseByFlowList: StateFlow<Apod?> = responseByFlowList_

    private val dataMutex = Mutex()

    fun getSingleItemData() {
        viewModelScope.launch {
            kotlin.runCatching {
                Repository.getData()
            }.onSuccess {
                responseFlow_.value = it
            }.onFailure { throwable ->
                responseFlow_.value = null
                throwable.message?.let { it -> Log.d("Error:", it) }
            }
        }
    }

    fun getDataListByCount(count: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                Repository.getDataList(count)
            }.onSuccess {
                responseFlowList_.value = it
            }.onFailure { throwable ->
                responseFlowList_.value = emptyList()
                throwable.message?.let { it -> Log.d("Error:", it) }
            }
        }
    }

    fun getDataListByDate(date: String) = viewModelScope.launch {

        dataMutex.withLock {
            kotlin.runCatching {
                Repository.getDataByDate(date)
            }.onSuccess {
                responseByFlowList_.value = it
            }.onFailure { throwable ->
                responseByFlowList_.value = null
                throwable.message?.let { it -> Log.d("Error:", it) }
            }
        }
    }

    private fun getDataDateFlow(date: String) = flow {
        val response = Repository.getDataByDate(date)
        if (response != null) {
            emit(response)
        } else {
            emit(null)
        }
    }

    fun getData(date: String) = viewModelScope.launch(Dispatchers.IO) {
        getDataDateFlow(date).collect {
            responseByFlowList_.value = it
        }
    }
}