package me.ahch.image_search_data.repository

import androidx.paging.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import me.ahch.core.model.Hit
import me.ahch.core.utils.Resource
import me.ahch.image_search_data.local.PixabayDatabase
import me.ahch.image_search_data.mapper.toHit
import me.ahch.image_search_data.paging.SearchRemoteMediator
import me.ahch.image_search_data.remote.SearchApi
import me.ahch.image_search_data.util.Constants.ITEMS_PER_PAGE
import me.ahch.image_search_data.util.toPixabayQuery
import me.ahch.image_search_domain.repository.SearchRepository
import java.lang.Exception

@OptIn(ExperimentalPagingApi::class)
class SearchRepositoryImpl(
    private val api: SearchApi,
    private val pixabayDatabase: PixabayDatabase
) : SearchRepository {

    init {
        System.loadLibrary("api-keys")
    }

    external fun getPixabayApi(): String

    override suspend fun searchImage(query: String): Flow<Resource<PagingData<Hit>>> {
        val pagingSourceFactory = { pixabayDatabase.imagesDao().getAllImages() }

        val searchRemoteMediator = SearchRemoteMediator(
            searchApi = api,
            pixabayDatabase = pixabayDatabase,
            query = query.toPixabayQuery(),
            apiKey = getPixabayApi()
        )

        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = searchRemoteMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { Resource.Success(it.map { it.toHit() }) }
    }
}