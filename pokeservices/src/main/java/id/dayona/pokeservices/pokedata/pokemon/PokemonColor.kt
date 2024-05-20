package id.dayona.pokeservices.pokedata.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonColor(
	@field:SerializedName("name")
	val name: String? = null,
	@field:SerializedName("id")
	val id: Int? = null,
	@field:SerializedName("pokemon_species")
	val pokemonSpecies: List<PokemonSpeciesItem?>? = null
)

data class PokemonSpeciesItem(
	@field:SerializedName("name")
	val name: String? = null
)
