package id.dayona.pokeservices.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.dayona.pokeservices.pokedata.evochain.EvolutionChain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter

internal object Utilities {
  @Suppress("SdCardPath")
  private const val EVO_DB = "/data/user/0/id.dayona.pokebase/evo_chain.db"
  internal suspend fun saveEvoChainData(data: String) {
    try {
      val file = File(EVO_DB)
      val writer = withContext(Dispatchers.IO) {
        FileWriter(file)
      }
      withContext(Dispatchers.IO) {
        writer.write(data)
      }
      withContext(Dispatchers.IO) {
        writer.close()
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  internal fun readEvoChainData(): List<EvolutionChain?> {
    return try {
      val read = File(EVO_DB).readText(Charsets.UTF_8)
      val data: List<EvolutionChain?> =
        Gson().fromJson(read, object : TypeToken<List<EvolutionChain>>() {}.type)
      data.sortedBy { it?.id }
    } catch (e: Exception) {
      listOf()
    }
  }
}
