package id.dayona.pokeservices.data

import com.google.gson.annotations.SerializedName

data class EvoChain(

  @field:SerializedName("chain") val chain: Chain? = null,

  @field:SerializedName("baby_trigger_item") val babyTriggerItem: Any? = null,

  @field:SerializedName("id") val id: Int? = null,

  @field:SerializedName("sprites") val sprites: Sprites? = null,
)

data class Trigger(

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

data class EvolutionDetailsItem(

  @field:SerializedName("item") val item: Any? = null,

  @field:SerializedName("relative_physical_stats") val relativePhysicalStats: Any? = null,

  @field:SerializedName("turn_upside_down") val turnUpsideDown: Boolean? = null,

  @field:SerializedName("gender") val gender: Any? = null,

  @field:SerializedName("min_happiness") val minHappiness: Any? = null,

  @field:SerializedName("party_type") val partyType: Any? = null,

  @field:SerializedName("held_item") val heldItem: Any? = null,

  @field:SerializedName("known_move") val knownMove: Any? = null,

  @field:SerializedName("min_beauty") val minBeauty: Any? = null,

  @field:SerializedName("trade_species") val tradeSpecies: Any? = null,

  @field:SerializedName("trigger") val trigger: Trigger? = null,

  @field:SerializedName("needs_overworld_rain") val needsOverworldRain: Boolean? = null,

  @field:SerializedName("party_species") val partySpecies: Any? = null,

  @field:SerializedName("min_affection") val minAffection: Any? = null,

  @field:SerializedName("known_move_type") val knownMoveType: Any? = null,

  @field:SerializedName("time_of_day") val timeOfDay: String? = null,

  @field:SerializedName("location") val location: Any? = null,

  @field:SerializedName("min_level") val minLevel: Int? = null
)

data class EvolvesToItem(

  @field:SerializedName("evolution_details") val evolutionDetails: List<EvolutionDetailsItem?>? = null,

  @field:SerializedName("species") val speciesData: SpeciesData? = null,

  @field:SerializedName("evolves_to") val evolvesTo: List<Any?>? = null,

  @field:SerializedName("is_baby") val isBaby: Boolean? = null
)

data class Chain(

  @field:SerializedName("evolution_details") val evolutionDetails: List<Any?>? = null,

  @field:SerializedName("species") val speciesData: Species? = null,

  @field:SerializedName("evolves_to") val evolvesTo: List<EvolvesToItem?>? = null,

  @field:SerializedName("is_baby") val isBaby: Boolean? = null
)

data class Species(
  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

