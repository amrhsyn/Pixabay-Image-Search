package me.ahch.image_search_domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import me.ahch.core.model.Hit
import me.ahch.core.utils.Resource
import me.ahch.image_search_domain.repository.SearchRepository
import javax.inject.Inject

class SearchImageUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(query: String): Flow<PagingData<Hit>> = repository.searchImage(query)
    }
