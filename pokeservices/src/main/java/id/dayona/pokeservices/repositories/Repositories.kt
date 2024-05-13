package id.dayona.pokeservices.repositories

import id.dayona.pokeservices.data.Abilities
import id.dayona.pokeservices.data.Pokemon
import id.dayona.pokeservices.network.Core
import kotlinx.coroutines.flow.Flow

interface Repositories {
  suspend fun getPokemon(id: String): Flow<Core<Pokemon>>
  suspend fun getAbilities(): Flow<Core<Abilities>>
}