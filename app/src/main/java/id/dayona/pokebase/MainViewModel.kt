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
import id.dayona.pokeservices.pokedata.pokemon.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
  private val pokeServices = PokeServices()
  var pokemon by mutableStateOf<Pokemon?>(null)
  var evolutionChainList by mutableStateOf(listOf<EvolutionChain?>())
  val defaultEvolutionChainSize = pokeServices.repositories.evolutionChainSize()
  var evolutionChainProgress by mutableIntStateOf(0)
  var searchController by mutableStateOf("")
  private val mediaPlayer = MediaPlayer()
  var screenSize by mutableStateOf(IntSize.Zero)
  val MAX_ATB = 300
  var getPokeLoading by mutableStateOf(false)


  init {
    getEvolutionChainList()
    mediaPlayer.setAudioAttributes(
      AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
    )
  }


  private fun getEvolutionChainList() {
    viewModelScope.launch(Dispatchers.IO) {
      pokeServices.repositories.getEvolutionChainList().collectLatest {
        when (it) {
          is CoreError -> {
            println(it.message)
          }

          is CoreException -> {
            println(it.e)
          }

          is CoreSuccess -> {
            evolutionChainList = it.data
            evolutionChainProgress = (it.data.size * 100) / defaultEvolutionChainSize
          }

          CoreTimeout -> {
            println(it.toString())
          }

          Loading -> {}
        }
      }
    }
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
    evolutionChainList = pokeServices.repositories.searchEvolutionChainList(searchController)
  }

  fun calculateBaseAtb(atb: Float): Int {
    println((atb.toInt() * screenSize.width.div(MAX_ATB)) + (screenSize.width % MAX_ATB))
    return (atb.toInt() * screenSize.width.div(MAX_ATB)) + (screenSize.width % MAX_ATB)
  }
}