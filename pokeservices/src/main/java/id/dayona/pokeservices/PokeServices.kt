package id.dayona.pokeservices

import id.dayona.pokeservices.network.CoreError
import id.dayona.pokeservices.network.CoreException
import id.dayona.pokeservices.network.CoreSuccess
import id.dayona.pokeservices.network.CoreTimeout
import id.dayona.pokeservices.network.Loading
import id.dayona.pokeservices.repositories.RepoImpl
import id.dayona.pokeservices.repositories.Repositories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PokeServices {
  val repositories: Repositories = RepoImpl()

  init {

  }

  private fun getEvolist() {
    CoroutineScope(Dispatchers.IO).launch {
      repositories.getEvolutionChainList().collectLatest {
        when (it) {
          is CoreError -> {
            println(it.message)
          }

          is CoreException -> {
            println(it.e)
          }

          is CoreSuccess -> {
            println("${it.data?.data?.size}")
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
}