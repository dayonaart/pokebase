package id.dayona.pokebase

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import id.dayona.pokeservices.PokeServices
import id.dayona.pokeservices.data.Pokemon
import id.dayona.pokeservices.network.CoreError
import id.dayona.pokeservices.network.CoreException
import id.dayona.pokeservices.network.CoreSuccess
import id.dayona.pokeservices.network.CoreTimeout
import id.dayona.pokeservices.network.Loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
  private val pokeServices = PokeServices()
  var searchController by mutableStateOf("")
  var pokeList by mutableStateOf<List<Pokemon?>>(listOf())
  val getDefaultPokemon = 320
  private val mediaPlayer = MediaPlayer()

  init {
    getPokemon()
    mediaPlayer.setAudioAttributes(
      AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
    )
  }


  private fun getPokemon() {
    viewModelScope.launch(Dispatchers.IO) {
      delay(1000)
      pokeList = Utilities.readPokemon() ?: listOf()
      if (pokeList.size >= getDefaultPokemon) return@launch
      (pokeList.size + 1..getDefaultPokemon).forEachIndexed { _, i ->
        pokeServices.repositories.getPokemon("$i").collectLatest {
          when (it) {
            is CoreError -> {
              pokeList += null
            }

            is CoreException -> {
              pokeList += null
            }

            is CoreSuccess -> {
              println("get ${it.data.id} : ${it.data.name}")
              pokeList += it.data
              Utilities.savePokemon(Gson().toJson(pokeList))
            }

            CoreTimeout -> {
              pokeList += null
            }

            Loading -> {
            }
          }
        }
      }
    }
  }

  fun cries(url: String?) {
    viewModelScope.launch(Dispatchers.IO) {
      try {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepare()
        mediaPlayer.start()
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }

  fun onSearchChange(t: String) {
    searchController = t
    searchPokemon()
  }

  private fun searchPokemon() {
    val f = Utilities.readPokemon()?.filter { it?.name?.startsWith(searchController) ?: false }
    pokeList = if (f?.isEmpty() == true) {
      Utilities.readPokemon() ?: listOf()
    } else {
      f ?: listOf()
    }
  }
}