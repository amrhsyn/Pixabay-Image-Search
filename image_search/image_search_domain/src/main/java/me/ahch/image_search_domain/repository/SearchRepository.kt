package me.ahch.image_search_domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.ahch.core.model.Hit
import me.ahch.core.utils.Resource

interface SearchRepository {

    suspend fun searchImage(
        query: String
    ): Flow<Resource<PagingData<Hit>>>
}