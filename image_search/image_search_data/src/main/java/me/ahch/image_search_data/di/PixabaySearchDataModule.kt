package me.ahch.image_search_data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.ahch.image_search_data.BuildConfig
import me.ahch.image_search_data.local.PixabayDatabase
import me.ahch.image_search_data.remote.SearchApi
import me.ahch.image_search_data.repository.SearchRepositoryImpl
import me.ahch.image_search_data.util.Constants.IMAGES_DATABASE
import me.ahch.image_search_domain.repository.SearchRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PixabaySearchDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideFleetApi(client: OkHttpClient): SearchApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSearchRepository(
          api: SearchApi,
          pixabayDatabase: PixabayDatabase
    ): SearchRepository {
        return SearchRepositoryImpl(
            api = api,
            pixabayDatabase = pixabayDatabase
        )
    }


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): PixabayDatabase {
        return Room.databaseBuilder(
            context,
            PixabayDatabase::class.java,
            IMAGES_DATABASE
        ).build()
    }

}








