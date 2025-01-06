package com.bii.pokemondex.data.repository

import coil3.network.HttpException
import com.bii.pokemondex.domain.model.models.PokemonModel
import com.bii.pokemondex.domain.model.response.PokemonListModel
import com.bii.pokemondex.domain.repository.ApiRepository
import com.bii.pokemondex.ui.util.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException

class ApiRepositoryImpl(
    private val httpClient: HttpClient
) : ApiRepository {

    companion object {
        const val baseUrl = "https://pokeapi.co/api/v2/"
    }

    override suspend fun getAllPokemons(limit: Int, offset: Int): ApiResponse<PokemonListModel> {
        val pokemonList = try {
            httpClient.get("pokemon?offset=$offset&limit=$limit").body<PokemonListModel>()
        } catch (e: IOException) {
            return ApiResponse.Error("Network error occurred while fetching all Pokémon. Please check your connection.")
        } catch (e: SerializationException) {
            return ApiResponse.Error("Data format error occurred while fetching all Pokémon.")
        } catch (e: HttpException) {
            return ApiResponse.Error("HTTP error occurred.")
        } catch (e: Exception) {
            return ApiResponse.Error("An unexpected error occurred: ${e.localizedMessage}")
        }
        return ApiResponse.Success(pokemonList)
    }

    override suspend fun getPokemonByName(name: String): ApiResponse<PokemonModel> {
        val pokemon = try {
            httpClient.get("pokemon/${name.lowercase()}").body<PokemonModel>()
        } catch (e: Exception) {
            return ApiResponse.Error("An unknown error occured: ${e.localizedMessage}")
        }
        return ApiResponse.Success(pokemon)
    }
}