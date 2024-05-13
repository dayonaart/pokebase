package id.dayona.pokebase

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.dayona.pokeservices.data.Pokemon
import java.io.File
import java.io.FileWriter


@SuppressLint("StaticFieldLeak")
object Utilities {
  lateinit var context: Context
  fun savePokemon(data: String) {
    try {
      val file = File("${context.cacheDir}/datas.db")
      val writer = FileWriter(file)
      writer.write(data)
      writer.close()
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  fun readPokemon(): List<Pokemon?>? {
    return try {
      val read = File("${context.cacheDir}/datas.db").readText(Charsets.UTF_8)
      val data: List<Pokemon?> = Gson().fromJson(read, object : TypeToken<List<Pokemon>>() {}.type)
      data.sortedBy { it?.id }
    } catch (e: Exception) {
      null
    }
  }
}