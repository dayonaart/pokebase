package id.dayona.pokeservices.data

import com.google.gson.annotations.SerializedName

data class Pokemon(

  @field:SerializedName("cries")
  val cries: Cries? = null,

  @field:SerializedName("types")
  val types: List<TypesItem?>? = null,

  @field:SerializedName("base_experience")
  val baseExperience: Int? = null,

  @field:SerializedName("name")
  val name: String? = null,

  @field:SerializedName("weight")
  val weight: Int? = null,

  @field:SerializedName("id")
  val id: Int? = null,

  @field:SerializedName("is_default")
  val isDefault: Boolean? = null,

  @field:SerializedName("sprites")
  val sprites: Sprites? = null,

  @field:SerializedName("height")
  val height: Int? = null,

  @field:SerializedName("order")
  val order: Int? = null
)

data class Home(

  @field:SerializedName("front_shiny_female")
  val frontShinyFemale: Any? = null,

  @field:SerializedName("front_default")
  val frontDefault: String? = null,

  @field:SerializedName("front_female")
  val frontFemale: Any? = null,

  @field:SerializedName("front_shiny")
  val frontShiny: String? = null
)

data class Cries(

  @field:SerializedName("legacy")
  val legacy: String? = null,

  @field:SerializedName("latest")
  val latest: String? = null
)

data class Showdown(

  @field:SerializedName("back_shiny_female")
  val backShinyFemale: Any? = null,

  @field:SerializedName("back_female")
  val backFemale: Any? = null,

  @field:SerializedName("back_default")
  val backDefault: String? = null,

  @field:SerializedName("front_shiny_female")
  val frontShinyFemale: Any? = null,

  @field:SerializedName("front_default")
  val frontDefault: String? = null,

  @field:SerializedName("front_female")
  val frontFemale: Any? = null,

  @field:SerializedName("back_shiny")
  val backShiny: String? = null,

  @field:SerializedName("front_shiny")
  val frontShiny: String? = null
)

data class Type(

  @field:SerializedName("name")
  val name: String? = null,

  @field:SerializedName("url")
  val url: String? = null
)

data class Sprites(

  @field:SerializedName("back_shiny_female")
  val backShinyFemale: Any? = null,

  @field:SerializedName("back_female")
  val backFemale: Any? = null,

  @field:SerializedName("other")
  val other: Other? = null,

  @field:SerializedName("back_default")
  val backDefault: String? = null,

  @field:SerializedName("front_shiny_female")
  val frontShinyFemale: Any? = null,

  @field:SerializedName("front_default")
  val frontDefault: String? = null,

  @field:SerializedName("front_female")
  val frontFemale: Any? = null,

  @field:SerializedName("back_shiny")
  val backShiny: String? = null,

  @field:SerializedName("front_shiny")
  val frontShiny: String? = null
)

data class OfficialArtwork(

  @field:SerializedName("front_default")
  val frontDefault: String? = null,

  @field:SerializedName("front_shiny")
  val frontShiny: String? = null
)

data class Other(

  @field:SerializedName("dream_world")
  val dreamWorld: DreamWorld? = null,

  @field:SerializedName("showdown")
  val showdown: Showdown? = null,

  @field:SerializedName("official-artwork")
  val officialArtwork: OfficialArtwork? = null,

  @field:SerializedName("home")
  val home: Home? = null
)

data class DreamWorld(

  @field:SerializedName("front_default")
  val frontDefault: String? = null,

  @field:SerializedName("front_female")
  val frontFemale: Any? = null
)

data class TypesItem(

  @field:SerializedName("slot")
  val slot: Int? = null,

  @field:SerializedName("type")
  val type: Type? = null
)
