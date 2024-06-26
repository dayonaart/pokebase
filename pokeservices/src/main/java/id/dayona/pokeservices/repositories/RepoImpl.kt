package id.dayona.pokeservices.repositories

import com.google.gson.Gson
import id.dayona.pokeservices.network.Core
import id.dayona.pokeservices.network.CoreError
import id.dayona.pokeservices.network.CoreException
import id.dayona.pokeservices.network.CoreSuccess
import id.dayona.pokeservices.network.CoreTimeout
import id.dayona.pokeservices.network.Loading
import id.dayona.pokeservices.network.Module
import id.dayona.pokeservices.pokedata.evochain.EvolutionData
import id.dayona.pokeservices.pokedata.pokemon.Pokemon
import id.dayona.pokeservices.pokedata.pokemon.PokemonColor
import id.dayona.pokeservices.pokedata.species.Species
import id.dayona.pokeservices.util.Utilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal class RepoImpl : Repositories {
  override fun getEvolutionChainList(): Flow<Core<EvolutionData?>> {
    return flow {
      emit(Loading)
      try {
        delay(500)
        val existing = Utilities.readEvoChainData().toMutableList()
        if (existing.size >= Constant.POKEMON_SIZE) {
          emit(
            CoreSuccess(
              data = EvolutionData(
                data = existing.toList(), progress = 100
              )
            )
          )
        }
        ((existing.size + 1)..Constant.POKEMON_SIZE).forEachIndexed { _, i ->
          val evo = Module.repo().getEvolutionChain("$i")
          existing += if (evo.isSuccessful) {
            val sprite = Module.repo().getPokemon("${evo.body()?.chain?.species?.name}")
            if (sprite.isSuccessful) {
              evo.body()?.copy(sprites = sprite.body()?.sprites)
            } else {
              evo.body()
            }
          } else {
            evo.body()
          }
          val data = Gson().toJson(existing.toList())
          Utilities.saveEvoChainData(data)
          emit(
            CoreSuccess(
              data = EvolutionData(
                data = existing.toList(), progress = i * 100 / Constant.POKEMON_SIZE
              )
            )
          )
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
    }.flowOn(Dispatchers.IO)
  }

  override fun getPokemon(id: String): Flow<Core<Pokemon?>> {
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
    }.flowOn(Dispatchers.IO)
  }

  override fun getColors(): Flow<Core<List<PokemonColor?>>> {
    return flow {
      emit(Loading)
      try {
        if (Utilities.readEvoColorsData().isNotEmpty()) {
          emit(CoreSuccess(data = Utilities.readEvoColorsData()))
          return@flow
        }
        val data = mutableListOf<PokemonColor?>()
        (1..10).forEachIndexed { _, i ->
          val res = Module.repo().getColor("$i")
          if (res.isSuccessful) {
            data += res.body()
          } else {
            data += null
          }
          Utilities.saveEvoColorsData(Gson().toJson(data))
          emit(CoreSuccess(data = data))
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
    }.flowOn(Dispatchers.IO)
  }

  override fun getPokemonColor(name: String): PokemonColor? {
    val color = Utilities.readEvoColorsData()
      .find { it?.pokemonSpecies?.find { n -> n?.name == name }?.name != null }
    return color
  }

  override fun getSpecies(id: String): Flow<Core<Species?>> {
    return flow {
      emit(Loading)
      try {
        val res = Module.repo().getSpecies(id)
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
    }.flowOn(Dispatchers.IO)
  }

  override fun getEvoDatabase(): Flow<Core<EvolutionData>> {
    return flow {
      emit(Loading)
      if (Utilities.readEvoChainData().isNotEmpty()) {
        emit(
          CoreSuccess(
            data = EvolutionData(
              progress = 100, data = Utilities.readEvoChainData()
            )
          )
        )
        return@flow
      }
      val body = Module.privateRepo().downloadDatabase().body()
      if (body == null) {
        emit(CoreError(code = 999, message = "Error while downloading database"))
        return@flow
      }
      try {
        body.byteStream().use { inputStream ->
          Utilities.EVO_EXTERNAL_FILE_DB.outputStream().use { outputStream ->
            val totalBytes = body.contentLength()
            val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
            var progressBytes = 0L
            var bytes = inputStream.read(buffer)
            while (bytes >= 0) {
              outputStream.write(buffer, 0, bytes)
              progressBytes += bytes
              bytes = inputStream.read(buffer)
              val progress = ((progressBytes * 100) / totalBytes).toInt()
              if (progress == 100) {
                emit(
                  CoreSuccess(
                    data = EvolutionData(
                      progress = progress, data = Utilities.readEvoChainData()
                    )
                  )
                )
                body.close()
                outputStream.flush()
                return@flow
              }
              emit(
                CoreSuccess(
                  data = EvolutionData(
                    progress = progress, data = listOf()
                  )
                )
              )
            }
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
    }.flowOn(Dispatchers.IO)
  }

  override fun searchEvolutionChainList(name: String): EvolutionData {
    val f =
      Utilities.readEvoChainData().filter { it?.chain?.species?.name?.startsWith(name) ?: false }
    return if (f.isEmpty()) {
      EvolutionData(data = Utilities.readEvoChainData())
    } else {
      EvolutionData(data = f)
    }
  }

  override fun sortEvolutionChainList(asc: Boolean?): EvolutionData {
    val data = Utilities.readEvoChainData().sortedBy { it?.chain?.species?.name }
    if (asc == null) {
      return EvolutionData(data = Utilities.readEvoChainData())
    }
    return if (asc) {
      EvolutionData(data = data)
    } else {
      EvolutionData(data = data.reversed())
    }
  }
}