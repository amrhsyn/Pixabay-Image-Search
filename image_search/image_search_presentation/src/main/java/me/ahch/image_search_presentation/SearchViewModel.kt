package me.ahch.image_search_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.ahch.core.model.Hit
import me.ahch.image_search_domain.usecase.SearchImageUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchImageUseCase: SearchImageUseCase,
) : ViewModel() {

    private var _hitListPagingData = MutableStateFlow<PagingData<Hit>>(PagingData.empty())
    val hitListPagingData = _hitListPagingData

    private var _state = MutableStateFlow(SearchState())
    val state = _state


    init {
        searchImage("flowers")
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnTextChange -> {
                _state.value = state.value.copy(searchedValue = event.text)
                searchImage()
            }
            is SearchEvent.OnSearchClick -> {
                _state.value = state.value.copy(searchedValue = event.text)
                searchImage()
            }
            is SearchEvent.OnCloseClick -> {
                _state.value = state.value.copy(searchedValue = "")
            }
            is SearchEvent.OnAlertDialogDismiss -> {
                _state.value = state.value.copy(isDialogOpen = false)
            }
            is SearchEvent.OnAlertDialogApply -> {
                _state.value = state.value.copy(isDialogOpen = false)
            }
            is SearchEvent.OnSearchItemClick -> {
                _state.value = state.value.copy(selectedHit = event.hit)
                _state.value = state.value.copy(isDialogOpen = true)

            }

        }
    }


    private fun searchImage(query: String = state.value.searchedValue) {
        viewModelScope.launch {
            searchImageUseCase.invoke(query = query).cachedIn(viewModelScope).collect {
                _hitListPagingData.value = it
            }
        }
    }

}