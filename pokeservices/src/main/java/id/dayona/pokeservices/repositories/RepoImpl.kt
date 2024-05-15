package id.dayona.pokeservices.repositories

import com.google.gson.Gson
import id.dayona.pokeservices.network.Core
import id.dayona.pokeservices.network.CoreError
import id.dayona.pokeservices.network.CoreException
import id.dayona.pokeservices.network.CoreSuccess
import id.dayona.pokeservices.network.CoreTimeout
import id.dayona.pokeservices.network.Loading
import id.dayona.pokeservices.network.Module
import id.dayona.pokeservices.pokedata.evochain.EvolutionChain
import id.dayona.pokeservices.pokedata.pokemon.Pokemon
import id.dayona.pokeservices.util.Utilities
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal class RepoImpl : Repositories {
  override fun evolutionChainSize(): Int {
    return Constant.POKEMON_SIZE
  }

  override suspend fun getEvolutionChainList(): Flow<Core<List<EvolutionChain?>>> {
    return flow {
      emit(Loading)
      try {
        delay(500)
        val existing = Utilities.readEvoChainData().toMutableList()
        if (existing.size >= Constant.POKEMON_SIZE) {
          emit(CoreSuccess(data = existing.toList()))
        }
        ((existing.size + 1)..Constant.POKEMON_SIZE).forEachIndexed { _, i ->
          val evo = Module.repo().getEvolutionChain("$i")
          if (evo.isSuccessful) {
            val sprite = Module.repo().getPokemon("${evo.body()?.chain?.species?.name}")
            if (sprite.isSuccessful) {
              existing += evo.body()?.copy(sprites = sprite.body()?.sprites)
              Utilities.saveEvoChainData(Gson().toJson(existing.toList()))
              emit(CoreSuccess(data = existing.toList()))
            } else {
              existing += evo.body()
              Utilities.saveEvoChainData(Gson().toJson(existing.toList()))
              emit(CoreSuccess(data = existing.toList()))
            }
          } else {
            existing += evo.body()
            Utilities.saveEvoChainData(Gson().toJson(existing.toList()))
            emit(CoreSuccess(data = existing.toList()))
          }

        }
      } catch (e: SocketTimeoutException) {
        emit(CoreTimeout)
      } catch (e: SocketException) {
        emit(CoreException(e = "${e.message}"))
      } catch (e: UnknownHostException) {
        emit(CoreException(e = "${e.message}"))
      } catch (e: IOException) {
        emit(CoreException(e = "${e.message}"))
      } catch (e: HttpException) {
        emit(CoreException(e = "${e.message}"))
      } catch (e: Exception) {
        emit(CoreException(e = "${e.message}"))
      }
    }
  }

  override suspend fun getPokemon(id: String): Flow<Core<Pokemon?>> {
    return flow {
      emit(Loading)
      try {
        val res = Module.repo().getPokemon(id)
        if (!res.isSuccessful) emit(CoreError(code = res.code(), message = res.message()))
        emit(CoreSuccess(data = res.body()))
      } catch (e: SocketTimeoutException) {
        emit(CoreTimeout)
      } catch (e: SocketException) {
        emit(CoreException(e = "${e.message}"))
      } catch (e: UnknownHostException) {
        emit(CoreException(e = "${e.message}"))
      } catch (e: IOException) {
        emit(CoreException(e = "${e.message}"))
      } catch (e: HttpException) {
        emit(CoreException(e = "${e.message}"))
      } catch (e: Exception) {
        emit(CoreException(e = "${e.message}"))
      }
    }
  }

  override fun searchEvolutionChainList(name: String): List<EvolutionChain?> {
    val f =
      Utilities.readEvoChainData().filter { it?.chain?.species?.name?.startsWith(name) ?: false }
    return f.ifEmpty {
      Utilities.readEvoChainData()
    }
  }
}