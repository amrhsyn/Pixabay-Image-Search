package me.ahch.image_search_presentation

import me.ahch.core.model.Hit

sealed class SearchEvent {
    data class OnTextChange(val text: String) : SearchEvent()
    data class OnSearchClick(val text: String) : SearchEvent()
    data class OnSearchItemClick(val hit: Hit) : SearchEvent()
    object OnCloseClick : SearchEvent()
    object OnAlertDialogDismiss : SearchEvent()
}
