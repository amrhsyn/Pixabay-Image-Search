package me.ahch.image_search_data.paging

import androidx.paging.*
import androidx.room.withTransaction
import me.ahch.image_search_data.dto.HitDto
import me.ahch.image_search_data.dto.ImagesRemoteKeys
import me.ahch.image_search_data.local.PixabayDatabase
import me.ahch.image_search_data.remote.SearchApi
import me.ahch.image_search_data.util.Constants

@OptIn(ExperimentalPagingApi::class)
class SearchRemoteMediator(
    private val searchApi: SearchApi,
    private val pixabayDatabase: PixabayDatabase,
    private val query: String,
    private val apiKey:String,
) : RemoteMediator<Int, HitDto>() {

    private val pixabayImageDao = pixabayDatabase.imagesDao()
    private val pixabayRemoteKeysDao = pixabayDatabase.imagesRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, HitDto>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = searchApi.searchImage(
                query = query,
                page = currentPage,
                perPage = Constants.ITEMS_PER_PAGE,
                key = apiKey
            )

            if (response.isSuccessful) {

                val hits = response.body()!!.hits
                val endOfPaginationReached = response.body()!!.hits.isEmpty()

                val prevPage = if (currentPage == 1) null else currentPage - 1
                val nextPage = if (endOfPaginationReached) null else currentPage + 1

                pixabayDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        pixabayImageDao.deleteAllImages()
                        pixabayRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val keys = hits.map { pixabayImage ->
                        ImagesRemoteKeys(
                            id = pixabayImage.id,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }
                    pixabayRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                    pixabayImageDao.addImages(images = hits)
                }
                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            } else {
                return MediatorResult.Error(Throwable(response.errorBody().toString()))
            }


        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, HitDto>
    ): ImagesRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                pixabayRemoteKeysDao.getRemoteKeys(id = id.toString())
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, HitDto>
    ): ImagesRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { pixabayImage ->
                pixabayRemoteKeysDao.getRemoteKeys(id = pixabayImage.id.toString())
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, HitDto>
    ): ImagesRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { pixabayImage ->
                pixabayRemoteKeysDao.getRemoteKeys(id = pixabayImage.id.toString())
            }
    }

}