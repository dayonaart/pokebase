package id.dayona.pokeservices.repositories

import id.dayona.pokeservices.data.EvoChain
import id.dayona.pokeservices.data.PokemonData
import id.dayona.pokeservices.data.SpeciesData
import id.dayona.pokeservices.network.Core
import kotlinx.coroutines.flow.Flow

interface Repositories {
  suspend fun getPokemon(id: String): Flow<Core<PokemonData>>
  suspend fun getSpecies(id: String): Flow<Core<SpeciesData>>
  suspend fun getEvoChain(id: String): Flow<Core<EvoChain>>
  suspend fun getSprite(id: String): Flow<Core<Any>>
  suspend fun getAnimation(id: String): Flow<Core<Any>>
}