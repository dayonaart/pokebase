package id.dayona.pokeservices.repositories

import id.dayona.pokeservices.network.Core
import id.dayona.pokeservices.pokedata.evochain.EvolutionData
import id.dayona.pokeservices.pokedata.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface Repositories {
  fun getEvolutionChainList(): Flow<Core<EvolutionData?>>
  fun searchEvolutionChainList(name: String): EvolutionData
  suspend fun getPokemon(id: String): Flow<Core<Pokemon?>>
  fun getEvoDatabase(): Flow<Core<EvolutionData>>
}