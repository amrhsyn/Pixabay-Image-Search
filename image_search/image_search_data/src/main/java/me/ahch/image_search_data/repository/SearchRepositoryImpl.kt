package me.ahch.image_search_data.repository

import me.ahch.core.model.Hit
import me.ahch.core.utils.Resource
import me.ahch.image_search_domain.repository.SearchRepository

class SearchRepositoryImpl:SearchRepository {

    override suspend fun searchImage(query: String): Resource<List<Hit>> {
        TODO("Not yet implemented")
    }

}