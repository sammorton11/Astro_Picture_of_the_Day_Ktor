package com.samm.ktor01.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samm.ktor01.core.Resource
import com.samm.ktor01.data.repository.Repository
import com.samm.ktor01.domain.models.Apod
import kotlinx.coroutines.flow.*

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

    private val responseFlow_ = MutableStateFlow<SingleItemScreenState?>(null)
    val responseFlow: StateFlow<SingleItemScreenState?> = responseFlow_

    private val responseFlowList_ = MutableStateFlow<ListViewScreenState?>(null)
    val responseFlowList: StateFlow<ListViewScreenState?> = responseFlowList_

    private val responseByDateFlowList_ = MutableStateFlow<DateSelectionScreenState?>(null)
    val responseByDateFlowList: StateFlow<DateSelectionScreenState?> = responseByDateFlowList_

    private fun getSingleItemDataFlow() = flow {
        val response = Repository.getData()
        emit(Resource.Loading())
        emit(Resource.Success(response))
    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unknown Error"))
    }

    private fun getDataListByCount(count: Int) = flow {
        val response = Repository.getDataList(count)
        emit(Resource.Loading())
        emit(Resource.Success(response))
    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unknown Error"))
    }

    private fun getDataDateFlow(date: String) = flow {
        val response = Repository.getDataByDate(date)
        emit(Resource.Loading())
        emit(Resource.Success(response))
    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unknown Error"))
    }

    fun getSingleItemData() {
        getSingleItemDataFlow().onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    responseFlow_.value = SingleItemScreenState(isLoading = true)
                }
                is Resource.Success -> {
                    responseFlow_.value = SingleItemScreenState(data = response.data)
                }
                is Resource.Error -> {
                    responseFlow_.value = SingleItemScreenState(error = response.message)
                }
            }
        }.launchIn(viewModelScope)
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
