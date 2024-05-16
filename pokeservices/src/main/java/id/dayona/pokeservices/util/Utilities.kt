package id.dayona.pokeservices.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.dayona.pokeservices.pokedata.evochain.EvolutionChain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter

internal object Utilities {
  enum class StoragePath { Movies, Notifications, Alarms, DCIM, Download, Documents, Music, Ringtones, Recordings, Pictures, Audiobooks }

  @Suppress("SdCardPath")
  val downloadPath = File(
    File("/sdcard").listFiles()
      ?.find { it.name.startsWith(StoragePath.Download.name) }, "evo_chain.db"
  )

  @Suppress("SdCardPath")
  private const val EVO_SYSTEM_DB = "/data/user/0/id.dayona.pokebase/evo_chain.db"

  private const val EVO_EXTERNAL_MEDIA_DBS =
    "/storage/emulated/0/Android/media/id.dayona.pokebase/evo_chain.db"
  private const val EVO_EXTERNAL_FILE_DBS =
    "/storage/emulated/0/Android/data/id.dayona.pokebase/files/database/evo_chain.db"


  internal suspend fun saveEvoChainData(data: String) {
    try {
      val writer = withContext(Dispatchers.IO) {
        FileWriter(downloadPath)
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
        downloadPath.readText(), object : TypeToken<List<EvolutionChain>>() {}.type
      )
      data.filterNotNull().sortedBy { it.id }
    } catch (e: Exception) {
      listOf()
    }
  }
}
