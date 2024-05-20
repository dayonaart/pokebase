package id.dayona.pokebase

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.dayona.pokeservices.PokeServices
import id.dayona.pokeservices.network.CoreError
import id.dayona.pokeservices.network.CoreException
import id.dayona.pokeservices.network.CoreSuccess
import id.dayona.pokeservices.network.CoreTimeout
import id.dayona.pokeservices.network.Loading
import id.dayona.pokeservices.pokedata.evochain.EvolutionChain
import id.dayona.pokeservices.pokedata.evochain.EvolutionData
import id.dayona.pokeservices.pokedata.pokemon.Pokemon
import id.dayona.pokeservices.pokedata.pokemon.PokemonColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

object MainModel {
  lateinit var mainViewModel: MainViewModel
}

class MainViewModel : ViewModel() {
  private val pokeServices = PokeServices()
  var pokeShow by mutableIntStateOf(50)
  var pokemon by mutableStateOf<Pokemon?>(null)
  var evoList by mutableStateOf(
    EvolutionData(
      data = listOf(), progress = 0
    )
  )
  var evoTable by mutableStateOf(
    EvolutionData(
      data = listOf(), progress = 0
    )
  )
  var searchController by mutableStateOf("")
  private val mediaPlayer = MediaPlayer()
  private var screenSize by mutableStateOf(IntSize.Zero)
  private val maxAtb = 300
  var getPokeLoading by mutableStateOf(false)
  var darkMode by mutableStateOf(false)


  init {
    getEvolutionList()
    mediaPlayer.setAudioAttributes(
      AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
    )
  }

  private fun getEvolutionList() {
    viewModelScope.launch(Dispatchers.IO) {
      pokeServices.repositories.getEvoDatabase().collectLatest {
        when (it) {
          is CoreError -> {
            println(it.message)
          }

          is CoreException -> {
            println(it.e)
          }

          is CoreSuccess -> {
            evoList = it.data
            evoTable = it.data
          }

          CoreTimeout -> {
            println(it.toString())
          }

          Loading -> {
            println(it.toString())
          }
        }
      }
    }
  }

  fun getPokemonShow(): List<EvolutionChain?> {
    return evoList.data.take(pokeShow)
  }

  fun getPokemon(id: String) {
    viewModelScope.launch(Dispatchers.IO) {
      pokeServices.repositories.getPokemon(id).collectLatest {
        when (it) {
          is CoreError -> {
            pokemon = null
            getPokeLoading = false
          }

          is CoreException -> {
            pokemon = null
            getPokeLoading = false
          }

          is CoreSuccess -> {
            pokemon = it.data
            getPokeLoading = false
          }

          CoreTimeout -> {
            pokemon = null
            getPokeLoading = false
          }

          Loading -> {
            getPokeLoading = true
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
    evoList = pokeServices.repositories.searchEvolutionChainList(searchController)
  }

  fun sortPokemon(asc: Boolean?) {
    evoList = pokeServices.repositories.sortEvolutionChainList(asc)
  }

  fun calculateBaseAtb(atb: Float): Int {
    println((atb.toInt() * screenSize.width.div(maxAtb)) + (screenSize.width % maxAtb))
    return (atb.toInt() * screenSize.width.div(maxAtb)) + (screenSize.width % maxAtb)
  }

  fun getColor(name: String): PokemonColor? {
    return pokeServices.repositories.getPokemonColor(name)
  }

  fun changeThemeMode() {
    darkMode = !darkMode
  }
}

