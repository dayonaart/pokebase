package id.dayona.pokeservices.repositories

import id.dayona.pokeservices.data.Abilities
import id.dayona.pokeservices.data.Pokemon
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

  override suspend fun getPokemon(id: String): Flow<Core<Pokemon>> {
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

  override suspend fun getAbilities(): Flow<Core<Abilities>> {
    return flow {
      emit(Loading)
      val res = Module.repo().getAbilities()
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
}