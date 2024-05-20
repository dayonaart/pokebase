package id.dayona.pokeservices.repositories

import id.dayona.pokeservices.network.Core
import id.dayona.pokeservices.pokedata.evochain.EvolutionData
import id.dayona.pokeservices.pokedata.pokemon.Pokemon
import id.dayona.pokeservices.pokedata.pokemon.PokemonColor
import kotlinx.coroutines.flow.Flow

interface Repositories {
  fun getEvolutionChainList(): Flow<Core<EvolutionData?>>
  fun searchEvolutionChainList(name: String): EvolutionData
  fun getPokemon(id: String): Flow<Core<Pokemon?>>
  fun getColors(): Flow<Core<List<PokemonColor?>>>
  fun getPokemonColor(name: String): PokemonColor?
  fun getEvoDatabase(): Flow<Core<EvolutionData>>
  fun sortEvolutionChainList(asc: Boolean?): EvolutionData
}