package id.dayona.pokeservices

import id.dayona.pokeservices.repositories.RepoImpl
import id.dayona.pokeservices.repositories.Repositories

class PokeServices {
  val repositories: Repositories = RepoImpl()
}