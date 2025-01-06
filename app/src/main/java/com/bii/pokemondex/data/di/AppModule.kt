package com.bii.pokemondex.data.di

import android.content.Context
import androidx.room.Room
import com.bii.pokemondex.data.local.dao.FavouritePokemonDao
import com.bii.pokemondex.data.local.database.AppDatabase
import com.bii.pokemondex.data.repository.ApiRepositoryImpl
import com.bii.pokemondex.data.repository.FavoritePokemonRepository
import com.bii.pokemondex.domain.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient =
        HttpClient(OkHttp.create()) {
            defaultRequest {
                url(ApiRepositoryImpl.baseUrl)
                header(HttpHeaders.ContentType, "application/json")
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }

    @Provides
    @Singleton
    fun provideApiRepository(httpClient: HttpClient): ApiRepository =
        ApiRepositoryImpl(httpClient = httpClient)

    @Provides
    fun provideFavoritePokemonRepository(
        dao: FavouritePokemonDao
    ): FavoritePokemonRepository {
        return FavoritePokemonRepository(dao)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database.db"
        ).build()
    }

    @Provides
    fun provideFavoritePokemonDao(database: AppDatabase): FavouritePokemonDao {
        return database.favoritePokemonDao()
    }
}
