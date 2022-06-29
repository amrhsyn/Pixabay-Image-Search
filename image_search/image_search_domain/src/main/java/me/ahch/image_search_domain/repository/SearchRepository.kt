package me.ahch.image_search_domain.repository

import me.ahch.core.model.Hit
import me.ahch.core.utils.Resource

interface SearchRepository {

    suspend fun searchImage(
        query: String
    ): Resource<List<Hit>>
}