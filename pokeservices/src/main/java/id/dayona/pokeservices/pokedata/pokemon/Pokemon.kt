package id.dayona.pokeservices.pokedata.pokemon

import com.google.gson.annotations.SerializedName
import id.dayona.pokeservices.pokedata.Animated
import id.dayona.pokeservices.pokedata.Emerald
import id.dayona.pokeservices.pokedata.GenerationIi
import id.dayona.pokeservices.pokedata.GenerationVii
import id.dayona.pokeservices.pokedata.OmegarubyAlphasapphire
import id.dayona.pokeservices.pokedata.Sprites
import id.dayona.pokeservices.pokedata.Yellow

data class Pokemon(

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

  @field:SerializedName("species") val species: Species? = null,

  @field:SerializedName("stats") val stats: List<StatsItem?>? = null,

  @field:SerializedName("moves") val moves: List<MovesItem?>? = null,

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("id") val id: Int? = null,

  @field:SerializedName("forms") val forms: List<FormsItem?>? = null,

  @field:SerializedName("height") val height: Int? = null,

  @field:SerializedName("order") val order: Int? = null,
)


data class RubySapphire(

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("back_shiny") val backShiny: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class Icons(

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_female") val frontFemale: Any? = null
)

data class Showdown(

  @field:SerializedName("back_shiny_female") val backShinyFemale: Any? = null,

  @field:SerializedName("back_female") val backFemale: Any? = null,

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_female") val frontFemale: Any? = null,

  @field:SerializedName("back_shiny") val backShiny: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class GenerationIv(

  @field:SerializedName("platinum") val platinum: Platinum? = null,

  @field:SerializedName("diamond-pearl") val diamondPearl: DiamondPearl? = null,

  @field:SerializedName("heartgold-soulsilver") val heartgoldSoulsilver: HeartgoldSoulsilver? = null
)

data class Silver(

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("back_shiny") val backShiny: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class XY(

  @field:SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_female") val frontFemale: Any? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class Platinum(

  @field:SerializedName("back_shiny_female") val backShinyFemale: Any? = null,

  @field:SerializedName("back_female") val backFemale: Any? = null,

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_female") val frontFemale: Any? = null,

  @field:SerializedName("back_shiny") val backShiny: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class Gold(

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("back_shiny") val backShiny: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class OfficialArtwork(

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class DreamWorld(

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_female") val frontFemale: Any? = null
)

data class Version(

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

data class HeldItemsItem(

  @field:SerializedName("item") val item: Item? = null,

  @field:SerializedName("version_details") val versionDetails: List<VersionDetailsItem?>? = null
)

data class Item(

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

data class VersionDetailsItem(

  @field:SerializedName("version") val version: id.dayona.pokeservices.pokedata.Version? = null,

  @field:SerializedName("rarity") val rarity: Int? = null
)

data class GenerationIii(

  @field:SerializedName("firered-leafgreen") val fireredLeafgreen: FireredLeafgreen? = null,

  @field:SerializedName("ruby-sapphire") val rubySapphire: RubySapphire? = null,

  @field:SerializedName("emerald") val emerald: Emerald? = null
)

data class Type(

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

data class StatsItem(

  @field:SerializedName("stat") val stat: Stat? = null,

  @field:SerializedName("base_stat") val baseStat: Int? = null,

  @field:SerializedName("effort") val effort: Int? = null
)


data class TypesItem(

  @field:SerializedName("slot") val slot: Int? = null,

  @field:SerializedName("type") val type: Type? = null
)

data class FormsItem(

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

data class FireredLeafgreen(

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("back_shiny") val backShiny: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)


data class Other(

  @field:SerializedName("dream_world") val dreamWorld: DreamWorld? = null,

  @field:SerializedName("showdown") val showdown: Showdown? = null,

  @field:SerializedName("official-artwork") val officialArtwork: OfficialArtwork? = null,

  @field:SerializedName("home") val home: Home? = null
)

data class GameIndicesItem(

  @field:SerializedName("game_index") val gameIndex: Int? = null,

  @field:SerializedName("version") val version: Version? = null
)

data class Versions(

  @field:SerializedName("generation-iii") val generationIii: GenerationIii? = null,

  @field:SerializedName("generation-ii") val generationIi: GenerationIi? = null,

  @field:SerializedName("generation-v") val generationV: GenerationV? = null,

  @field:SerializedName("generation-iv") val generationIv: GenerationIv? = null,

  @field:SerializedName("generation-vii") val generationVii: GenerationVii? = null,

  @field:SerializedName("generation-i") val generationI: GenerationI? = null,

  @field:SerializedName("generation-viii") val generationViii: GenerationViii? = null,

  @field:SerializedName("generation-vi") val generationVi: GenerationVi? = null
)

data class Species(

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

data class GenerationI(

  @field:SerializedName("yellow") val yellow: Yellow? = null,

  @field:SerializedName("red-blue") val redBlue: RedBlue? = null
)

data class VersionGroupDetailsItem(

  @field:SerializedName("level_learned_at") val levelLearnedAt: Int? = null,

  @field:SerializedName("version_group") val versionGroup: VersionGroup? = null,

  @field:SerializedName("move_learn_method") val moveLearnMethod: MoveLearnMethod? = null
)

data class VersionGroup(

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

data class DiamondPearl(

  @field:SerializedName("back_shiny_female") val backShinyFemale: Any? = null,

  @field:SerializedName("back_female") val backFemale: Any? = null,

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_female") val frontFemale: Any? = null,

  @field:SerializedName("back_shiny") val backShiny: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class UltraSunUltraMoon(

  @field:SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_female") val frontFemale: Any? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class GenerationV(

  @field:SerializedName("black-white") val blackWhite: BlackWhite? = null
)

data class Ability(

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

data class Crystal(

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("back_shiny") val backShiny: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class MovesItem(

  @field:SerializedName("version_group_details") val versionGroupDetails: List<VersionGroupDetailsItem?>? = null,

  @field:SerializedName("move") val move: Move? = null
)

data class HeartgoldSoulsilver(

  @field:SerializedName("back_shiny_female") val backShinyFemale: Any? = null,

  @field:SerializedName("back_female") val backFemale: Any? = null,

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_female") val frontFemale: Any? = null,

  @field:SerializedName("back_shiny") val backShiny: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class Home(

  @field:SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_female") val frontFemale: Any? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class Stat(

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

data class MoveLearnMethod(

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

data class Generation(

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

data class PastTypesItem(

  @field:SerializedName("generation") val generation: Generation? = null,

  @field:SerializedName("types") val types: List<TypesItem?>? = null
)

data class GenerationViii(

  @field:SerializedName("icons") val icons: Icons? = null
)

data class RedBlue(

  @field:SerializedName("front_gray") val frontGray: String? = null,

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("back_gray") val backGray: String? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null
)

data class GenerationVi(

  @field:SerializedName("omegaruby-alphasapphire") val omegarubyAlphasapphire: OmegarubyAlphasapphire? = null,

  @field:SerializedName("x-y") val xY: XY? = null
)

data class BlackWhite(

  @field:SerializedName("back_shiny_female") val backShinyFemale: Any? = null,

  @field:SerializedName("back_female") val backFemale: Any? = null,

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("animated") val animated: Animated? = null,

  @field:SerializedName("front_female") val frontFemale: Any? = null,

  @field:SerializedName("back_shiny") val backShiny: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class Cries(

  @field:SerializedName("legacy") val legacy: String? = null,

  @field:SerializedName("latest") val latest: String? = null
)

data class Move(

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

data class AbilitiesItem(

  @field:SerializedName("is_hidden") val isHidden: Boolean? = null,

  @field:SerializedName("slot") val slot: Int? = null,

  @field:SerializedName("ability") val ability: Ability? = null
)
