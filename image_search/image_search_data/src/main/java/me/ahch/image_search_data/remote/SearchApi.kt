package me.ahch.image_search_data.remote

import me.ahch.image_search_data.dto.ImageSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("/api/")
    suspend fun searchImage(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<ImageSearchResponse>

}