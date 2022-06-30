package me.ahch.image_search_presentation

import androidx.paging.PagingData
import me.ahch.core.model.Hit

data class SearchState(
    val searchedValue: String = "",
    val selectedHit: Hit = Hit(),
    val isDialogOpen: Boolean=false,
)
