package com.samm.ktor01.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.samm.ktor01.domain.models.Apod

class FavoritesViewModel : ViewModel() {
    private val _favorites = mutableStateListOf<Apod>()
    val favorites: List<Apod> = _favorites

    fun addToFavorites(item: Apod) {
        if (!_favorites.contains(item)) {
            _favorites.add(item)
        }
    }

    fun removeFromFavorites(item: Apod) {
        _favorites.remove(item)
    }
}