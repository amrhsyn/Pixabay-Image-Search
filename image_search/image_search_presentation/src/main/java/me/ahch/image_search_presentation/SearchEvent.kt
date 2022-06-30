package me.ahch.image_search_presentation

sealed class SearchEvent {
    data class OnTextChange(val text: String) : SearchEvent()
    data class OnSearchClick(val text: String) : SearchEvent()
    object OnCloseClick : SearchEvent()
}
