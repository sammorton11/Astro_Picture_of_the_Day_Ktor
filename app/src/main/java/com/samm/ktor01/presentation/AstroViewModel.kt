package com.samm.ktor01.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samm.ktor01.core.Resource
import com.samm.ktor01.data.Repository
import com.samm.ktor01.domain.Apod
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AstroViewModel: ViewModel() {
    data class DateSelectionScreenState(
        val isLoading: Boolean = false,
        val data: Apod? = null,
        val error: String? = null
    )

    data class ListViewScreenState(
        val isLoading: Boolean = false,
        val data: List<Apod?> = emptyList(),
        val error: String? = null
    )

    data class SingleItemScreenState(
        val isLoading: Boolean = false,
        val data: Apod? = null,
        val error: String? = null
    )

    val responseFlow_ = MutableStateFlow<Apod?>(null)
    val responseFlow: StateFlow<Apod?> = responseFlow_

    val responseFlowList_ = MutableStateFlow<ListViewScreenState?>(null)
    val responseFlowList: StateFlow<ListViewScreenState?> = responseFlowList_

    val responseByDateFlowList_ = MutableStateFlow<DateSelectionScreenState?>(null)
    val responseByDateFlowList: StateFlow<DateSelectionScreenState?> = responseByDateFlowList_

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

//    fun getDataListByCount(count: Int) {
//        viewModelScope.launch {
//            kotlin.runCatching {
//                Repository.getDataList(count)
//            }.onSuccess {
//                responseFlowList_.value = it
//            }.onFailure { throwable ->
//                responseFlowList_.value = emptyList()
//                throwable.message?.let { it -> Log.d("Error:", it) }
//            }
//        }
//    }
    fun getDataListByCount(count: Int) = flow {
        val response = Repository.getDataList(count)
        emit(Resource.Loading(isLoading = true))
        emit(Resource.Success(response))
    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unknown Error"))
    }

    fun getDataByList(count: Int) {
        getDataListByCount(count).onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    responseFlowList_.value = ListViewScreenState(isLoading = true)
                }
                is Resource.Success -> {
                    responseFlowList_.value = ListViewScreenState(data = response.data)
                }
                is Resource.Error -> {
                    responseFlowList_.value = ListViewScreenState(error = response.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getDataDateFlow(date: String) = flow {
        val response = Repository.getDataByDate(date)
        emit(Resource.Loading(isLoading = true))
        emit(Resource.Success(response))
    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unknown Error"))
    }

    fun getDataByDate(date: String) {
        getDataDateFlow(date).onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    responseByDateFlowList_.value = DateSelectionScreenState(isLoading = true)
                }
                is Resource.Success -> {
                    responseByDateFlowList_.value = DateSelectionScreenState(data = response.data)
                }
                is Resource.Error -> {
                   responseByDateFlowList_.value = DateSelectionScreenState(error = response.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}
