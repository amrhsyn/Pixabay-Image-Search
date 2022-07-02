package me.ahch.pixabaysearch.repository

import androidx.paging.PagingData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import me.ahch.core.model.Hit
import me.ahch.image_search_domain.repository.SearchRepository
import me.ahch.test_shared.ValidImageSearchResponse

class SearchRepositoryFake : SearchRepository {

    override suspend fun searchImage(query: String): Flow<PagingData<Hit>> {

        val listType = object : TypeToken<List<Hit>>() {}.type
        val hitList = Gson().fromJson<List<Hit>>(ValidImageSearchResponse, listType)

        val pagingData = PagingData.from(
            hitList
        )

        return flowOf(pagingData)

    }

}
