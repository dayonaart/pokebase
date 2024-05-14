package id.dayona.pokeservices.repositories

import id.dayona.pokeservices.data.EvoChain
import id.dayona.pokeservices.data.PokemonData
import id.dayona.pokeservices.data.SpeciesData
import id.dayona.pokeservices.network.Core
import id.dayona.pokeservices.network.CoreError
import id.dayona.pokeservices.network.CoreException
import id.dayona.pokeservices.network.CoreSuccess
import id.dayona.pokeservices.network.CoreTimeout
import id.dayona.pokeservices.network.Loading
import id.dayona.pokeservices.network.Module
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal class RepoImpl : Repositories {

  override suspend fun getPokemon(id: String): Flow<Core<PokemonData>> {
    return flow {
      emit(Loading)
      val res = Module.repo().getPokemon(id)
      try {
        if (!res.isSuccessful) {
          emit(CoreError(code = res.code(), message = res.message()))
          return@flow
        }
        emit(CoreSuccess(data = res.body()!!))
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

  override suspend fun getEvoChain(id: String): Flow<Core<EvoChain>> {
    return flow {
      emit(Loading)
      val res = Module.repo().getEvoChain(id)
      try {
        if (!res.isSuccessful) {
          emit(CoreError(code = res.code(), message = res.message()))
          return@flow
        }
        val data = res.body()!!
        val getSprite = Module.repo().getPokemon(data.chain?.speciesData?.name ?: "")
        if (!getSprite.isSuccessful) emit(CoreSuccess(data = res.body()!!))
        emit(CoreSuccess(data = res.body()!!.copy(sprites = getSprite.body()!!.sprites)))

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

  override suspend fun getSpecies(id: String): Flow<Core<SpeciesData>> {
    return flow {
      emit(Loading)
      val res = Module.repo().getSpecies(id)
      try {
        if (!res.isSuccessful) {
          emit(CoreError(code = res.code(), message = res.message()))
          return@flow
        }
        emit(CoreSuccess(data = res.body()!!))
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

  override suspend fun getSprite(id: String): Flow<Core<Any>> {
    TODO("Not yet implemented")
  }

  override suspend fun getAnimation(id: String): Flow<Core<Any>> {
    TODO("Not yet implemented")
  }

}