package id.dayona.pokeservices.data

import com.google.gson.annotations.SerializedName

data class PokemonData(

	@field:SerializedName("location_area_encounters") val locationAreaEncounters: String? = null,

	@field:SerializedName("cries") val cries: Cries? = null,

	@field:SerializedName("types") val types: List<TypesItem?>? = null,

	@field:SerializedName("base_experience") val baseExperience: Int? = null,

	@field:SerializedName("held_items") val heldItems: List<HeldItemsItem?>? = null,

	@field:SerializedName("weight") val weight: Int? = null,

	@field:SerializedName("is_default") val isDefault: Boolean? = null,

	@field:SerializedName("sprites") val sprites: Sprites? = null,

	@field:SerializedName("past_types") val pastTypes: List<PastTypesItem?>? = null,

	@field:SerializedName("abilities") val abilities: List<AbilitiesItem?>? = null,

	@field:SerializedName("game_indices") val gameIndices: List<GameIndicesItem?>? = null,

	@field:SerializedName("stats") val stats: List<StatsItem?>? = null,

	@field:SerializedName("moves") val moves: List<MovesItem?>? = null,

	@field:SerializedName("name") val name: String? = null,

	@field:SerializedName("id") val id: Int? = null,

	@field:SerializedName("forms") val forms: List<FormsItem?>? = null,

	@field:SerializedName("height") val height: Int? = null,

	@field:SerializedName("order") val order: Int? = null
)

data class FormsItem(

	@field:SerializedName("name") val name: String? = null,

	@field:SerializedName("url") val url: String? = null
)

data class VersionDetailsItem(

	@field:SerializedName("version") val version: Version? = null,

	@field:SerializedName("rarity") val rarity: Int? = null
)

data class PastTypesItem(

	@field:SerializedName("generation") val generation: Generation? = null,

	@field:SerializedName("types") val types: List<TypesItem?>? = null
)


data class Move(

	@field:SerializedName("name") val name: String? = null,

	@field:SerializedName("url") val url: String? = null
)


data class MoveLearnMethod(

	@field:SerializedName("name") val name: String? = null,

	@field:SerializedName("url") val url: String? = null
)

data class Cries(

	@field:SerializedName("legacy") val legacy: String? = null,

	@field:SerializedName("latest") val latest: String? = null
)

data class Yellow(

	@field:SerializedName("front_gray") val frontGray: String? = null,

	@field:SerializedName("back_default") val backDefault: String? = null,

	@field:SerializedName("back_gray") val backGray: String? = null,

	@field:SerializedName("front_default") val frontDefault: String? = null
)

data class StatsItem(

	@field:SerializedName("stat") val stat: Stat? = null,

	@field:SerializedName("base_stat") val baseStat: Int? = null,

	@field:SerializedName("effort") val effort: Int? = null
)


data class Stat(

	@field:SerializedName("name") val name: String? = null,

	@field:SerializedName("url") val url: String? = null
)


data class Animated(

	@field:SerializedName("back_shiny_female") val backShinyFemale: Any? = null,

	@field:SerializedName("back_female") val backFemale: Any? = null,

	@field:SerializedName("back_default") val backDefault: String? = null,

	@field:SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,

	@field:SerializedName("front_default") val frontDefault: String? = null,

	@field:SerializedName("front_female") val frontFemale: Any? = null,

	@field:SerializedName("back_shiny") val backShiny: String? = null,

	@field:SerializedName("front_shiny") val frontShiny: String? = null
)


data class MovesItem(

	@field:SerializedName("version_group_details") val versionGroupDetails: List<VersionGroupDetailsItem?>? = null,

	@field:SerializedName("move") val move: Move? = null
)

data class Type(

	@field:SerializedName("name") val name: String? = null,

	@field:SerializedName("url") val url: String? = null
)

data class AbilitiesItem(

	@field:SerializedName("is_hidden") val isHidden: Boolean? = null,

	@field:SerializedName("slot") val slot: Int? = null,

	@field:SerializedName("ability") val ability: Ability? = null
)

data class Item(

	@field:SerializedName("name") val name: String? = null,

	@field:SerializedName("url") val url: String? = null
)

data class HeldItemsItem(

	@field:SerializedName("item") val item: Item? = null,

	@field:SerializedName("version_details") val versionDetails: List<VersionDetailsItem?>? = null
)

data class TypesItem(

	@field:SerializedName("slot") val slot: Int? = null,

	@field:SerializedName("type") val type: Type? = null
)


data class VersionGroup(

	@field:SerializedName("name") val name: String? = null,

	@field:SerializedName("url") val url: String? = null
)

data class VersionGroupDetailsItem(

	@field:SerializedName("level_learned_at") val levelLearnedAt: Int? = null,

	@field:SerializedName("version_group") val versionGroup: VersionGroup? = null,

	@field:SerializedName("move_learn_method") val moveLearnMethod: MoveLearnMethod? = null
)

data class GameIndicesItem(

	@field:SerializedName("game_index") val gameIndex: Int? = null,

	@field:SerializedName("version") val version: Version? = null
)
