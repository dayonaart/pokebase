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
import id.dayona.pokeservices.data.EvoChain
import id.dayona.pokeservices.data.PokemonData
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
  var pokeList by mutableStateOf<List<PokemonData?>>(listOf())
  var pokemonData by mutableStateOf<PokemonData?>(null)
  var evoChain by mutableStateOf<List<EvoChain?>>(listOf())
  val getDefaultEvoChain = 10 * 20
  private val mediaPlayer = MediaPlayer()

  init {
    getEvoChain()
    mediaPlayer.setAudioAttributes(
      AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
    )
  }


  private fun getEvoChain() {
    viewModelScope.launch(Dispatchers.IO) {
      delay(1000)
      evoChain = Utilities.readEvoChainData() ?: listOf()
      if (evoChain.size >= getDefaultEvoChain) return@launch
      (evoChain.size + 1..getDefaultEvoChain).forEachIndexed { _, i ->
        pokeServices.repositories.getEvoChain("$i").collectLatest {
          when (it) {
            is CoreError -> {
              evoChain += null
            }

            is CoreException -> {
              evoChain += null
            }

            is CoreSuccess -> {
              println("evo chain ${it.data.id} ${it.data.chain?.speciesData?.name}")
              evoChain += it.data
              if (i % 10 == 0) {
                Utilities.saveEvoChainData(Gson().toJson(evoChain))
              }
            }

            CoreTimeout -> {
              evoChain += null
            }

            Loading -> {
            }
          }
        }
      }
    }
  }

  fun getPokemon(id: String) {
    pokemonData = null
    viewModelScope.launch(Dispatchers.IO) {
      pokeServices.repositories.getPokemon(id).collectLatest {
        when (it) {
          is CoreError -> {
            pokemonData = null
          }

          is CoreException -> {
            pokemonData = null
          }

          is CoreSuccess -> {
            pokemonData = it.data
          }

          CoreTimeout -> {
            pokemonData = null
          }

          Loading -> {}
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
    val f = Utilities.readEvoChainData()
      ?.filter { it?.chain?.speciesData?.name?.startsWith(searchController) ?: false }
    evoChain = if (f?.isEmpty() == true) {
      Utilities.readEvoChainData() ?: listOf()
    } else {
      f ?: listOf()
    }
  }
}