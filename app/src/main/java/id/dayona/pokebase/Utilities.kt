package id.dayona.pokebase

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.dayona.pokeservices.data.EvoChain
import id.dayona.pokeservices.data.PokemonData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter


@SuppressLint("StaticFieldLeak")
object Utilities {
  lateinit var context: Context
  private const val EVO_CHAIN_PATH = "evo_chain.db"
  private const val POKEMON_PATH = "pokemon.db"

  suspend fun saveEvoChainData(data: String) {
    try {
      val file = File("${context.cacheDir}/$EVO_CHAIN_PATH")
      val writer = withContext(Dispatchers.IO) {
        FileWriter(file)
      }
      withContext(Dispatchers.IO) {
        writer.write(data)
      }
      withContext(Dispatchers.IO) {
        writer.close()
      }
      println("SAVED EVO CHAIN")
      delay(500)
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  fun readEvoChainData(): List<EvoChain?>? {
    return try {
      val read = File("${context.cacheDir}/$EVO_CHAIN_PATH").readText(Charsets.UTF_8)
      val data: List<EvoChain?> =
        Gson().fromJson(read, object : TypeToken<List<EvoChain>>() {}.type)
      data.sortedBy { it?.id }
    } catch (e: Exception) {
      null
    }
  }

  suspend fun savePokemonData(data: String) {
    try {
      val file = File("${context.cacheDir}/$POKEMON_PATH")
      val writer = withContext(Dispatchers.IO) {
        FileWriter(file)
      }
      withContext(Dispatchers.IO) {
        writer.write(data)
      }
      withContext(Dispatchers.IO) {
        writer.close()
      }
      println("SAVED POKEMON")
      delay(500)
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  fun readPokemonData(): List<PokemonData?>? {
    return try {
      val read = File("${context.cacheDir}/$POKEMON_PATH").readText(Charsets.UTF_8)
      val data: List<PokemonData?> =
        Gson().fromJson(read, object : TypeToken<List<PokemonData>>() {}.type)
      data.sortedBy { it?.id }
    } catch (e: Exception) {
      null
    }
  }
}