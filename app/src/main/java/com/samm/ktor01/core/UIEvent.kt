package com.samm.ktor01.core

sealed class UIEvent {
    object GetSingleItemData : UIEvent()
    data class GetListItemsData(val count: Int) : UIEvent()
    data class GetDataByDate(val date: String) : UIEvent()
}
