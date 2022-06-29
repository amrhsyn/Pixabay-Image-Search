package me.ahch.image_search_data.repository

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.ahch.core.model.Hit
import me.ahch.image_search_data.local.PixabayDatabase
import me.ahch.image_search_data.mapper.toHit
import me.ahch.image_search_data.paging.SearchRemoteMediator
import me.ahch.image_search_data.remote.SearchApi
import me.ahch.image_search_data.util.Constants.ITEMS_PER_PAGE
import me.ahch.image_search_domain.repository.SearchRepository
import java.lang.Exception

@OptIn(ExperimentalPagingApi::class)
class SearchRepositoryImpl(
    private val api: SearchApi,
    private val pixabayDatabase: PixabayDatabase
) : SearchRepository {

    override suspend fun searchImage(query: String): Flow<PagingData<Hit>> {
        val pagingSourceFactory = { pixabayDatabase.imagesDao().getAllImages() }
       return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = SearchRemoteMediator(
                searchApi = api,
                pixabayDatabase = pixabayDatabase,
                query = query,
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { it.map { it.toHit() } }

    }
}