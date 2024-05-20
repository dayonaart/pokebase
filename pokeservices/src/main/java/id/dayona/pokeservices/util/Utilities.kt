package id.dayona.pokeservices.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.dayona.pokeservices.pokedata.evochain.EvolutionChain
import id.dayona.pokeservices.pokedata.pokemon.PokemonColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter

internal object Utilities {
  enum class StoragePath { Movies, Notifications, Alarms, DCIM, Download, Documents, Music, Ringtones, Recordings, Pictures, Audiobooks }

  @Suppress("SdCardPath")
  val EVO_DOWNLOAD_PATH = File(
    File("/sdcard").listFiles()?.find { it.name.startsWith(StoragePath.Download.name) },
    "evo_chain.db"
  )

  @Suppress("SdCardPath")
  val EVO_SYSTEM_DB = File("/data/user/0/id.dayona.pokebase/evo_chain.db")

  val EVO_EXTERNAL_MEDIA_DB = File(
    "/storage/emulated/0/Android/media/id.dayona.pokebase/evo_chain.db"
  )
  val EVO_EXTERNAL_FILE_DB = File(
    "/storage/emulated/0/Android/data/id.dayona.pokebase/files/database/evo_chain.db"
  )

  private val EVO_COLOR_EXTERNAL_FILE_DB = File(
    "/storage/emulated/0/Android/data/id.dayona.pokebase/files/database/evo_colors.db"
  )


  internal suspend fun saveEvoChainData(data: String) {
    try {
      val writer = withContext(Dispatchers.IO) {
        FileWriter(EVO_EXTERNAL_FILE_DB)
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

  internal suspend fun saveEvoColorsData(data: String) {
    try {
      val writer = withContext(Dispatchers.IO) {
        FileWriter(EVO_COLOR_EXTERNAL_FILE_DB)
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
      val data: List<EvolutionChain?> = Gson().fromJson(
        EVO_EXTERNAL_FILE_DB.readText(), object : TypeToken<List<EvolutionChain?>>() {}.type
      )
      data.filterNotNull().sortedBy { it.id }.toSet().toList()
    } catch (e: Exception) {
      listOf()
    }
  }

  internal fun readEvoColorsData(): List<PokemonColor?> {
    return try {
      val data: List<PokemonColor?> = Gson().fromJson(
        EVO_COLOR_EXTERNAL_FILE_DB.readText(), object : TypeToken<List<PokemonColor?>>() {}.type
      )
      data.filterNotNull().sortedBy { it.id }.toSet().toList()
    } catch (e: Exception) {
      listOf()
    }
  }
}
