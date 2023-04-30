package com.samm.ktor01.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samm.ktor01.core.Resource
import com.samm.ktor01.core.UIEvent
import com.samm.ktor01.domain.models.Apod
import com.samm.ktor01.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AstroViewModel(
    private val repository: Repository
) : ViewModel() {

    private val responseFlow_ = MutableStateFlow<SingleItemScreenState?>(null)
    val responseFlow = responseFlow_.asStateFlow()

    private val responseFlowList_ = MutableStateFlow<ListViewScreenState?>(null)
    val responseFlowList = responseFlowList_.asStateFlow()

    private var responseByDateFlowList_ = MutableStateFlow<DateSelectionScreenState?>(null)
    val responseByDateFlowList = responseByDateFlowList_.asStateFlow()

    private val _favorites = MutableStateFlow<List<Apod>>(emptyList())
    val favorites: StateFlow<List<Apod>> = _favorites


    private fun getSingleItemDataFlow() = flow {
        emit(Resource.Loading())
        val response = repository.getData()
        Log.d("AstroViewModel", "getSingleItemDataFlow: ${response.date}")
        emit(Resource.Success(response))

    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unknown Error"))
    }

    private fun getDataListByCountFlow(count: Int) = flow {
        emit(Resource.Loading())
        val response = repository.getDataList(count)
        Log.d("response", response.toString())
        emit(Resource.Success(response))
    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unknown Error"))
    }

    private fun getDataDateFlow(date: String) = flow {
        emit(Resource.Loading())
        val response = repository.getDataByDate(date)
        emit(Resource.Success(response))
    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unknown Error"))
    }

    private fun getSingleItemData() {
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

    private fun getDataByList(count: Int) {
        getDataListByCountFlow(count).onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    responseFlowList_.value = ListViewScreenState(isLoading = true)
                    Log.d("responseFlowList_", responseFlowList_.value.toString())
                }
                is Resource.Success -> {
                    responseFlowList_.value = ListViewScreenState(data = response.data)
                    Log.d("responseFlowList_", responseFlowList_.value.toString())
                }
                is Resource.Error -> {
                    responseFlowList_.value = ListViewScreenState(error = response.message)
                    Log.d("responseFlowList_", responseFlowList_.value.toString())
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
                    responseByDateFlowList_.value =
                        DateSelectionScreenState(error = response.message)
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun handleEvent(event: UIEvent) {
        when (event) {
            is UIEvent.GetSingleItemData -> getSingleItemData()
            is UIEvent.GetListItemsData -> getDataByList(event.count)
            is UIEvent.GetDataByDate -> getDataByDate(event.date)
            is UIEvent.GetFavorites -> getAllFavorites()
        }
    }

    fun sendEvent(event: UIEvent) = viewModelScope.launch {
        handleEvent(event)
    }

    // Database
    fun insertFavorite(apod: Apod) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavorite(apod)
        }
    }

    fun deleteFavorite(apod: Apod) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorite(apod)
        }
    }

    private fun getAllFavorites() {
            viewModelScope.launch {
                repository.getAllFavorites().collect { favorites ->
                _favorites.value = favorites
                }
            }
        }
    }


// State
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