package com.samm.ktor01

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AstroViewModel: ViewModel() {

    val responseFlow = MutableStateFlow<Response?>(null)

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                Repository.getData()
            }.onSuccess {
                responseFlow.value = it
            }.onFailure { throwable ->
                responseFlow.value = null
                throwable.message?.let { it -> Log.d("Error:", it) }
            }
            Log.d("Error2:", Repository.getData().toString())
        }
    }
}