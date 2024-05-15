package id.dayona.pokeservices.repositories

import id.dayona.pokeservices.network.Core
import id.dayona.pokeservices.pokedata.evochain.EvolutionChain
import id.dayona.pokeservices.pokedata.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface Repositories {
  suspend fun getEvolutionChainList(): Flow<Core<List<EvolutionChain?>>>
  fun searchEvolutionChainList(name: String): List<EvolutionChain?>
  fun evolutionChainSize(): Int
  suspend fun getPokemon(id: String): Flow<Core<Pokemon?>>
}