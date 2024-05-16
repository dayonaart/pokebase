package id.dayona.pokeservices.pokedata

import com.google.gson.annotations.SerializedName

data class Pokemon(
  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

data class Ability(
  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

data class NamesItem(

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("language") val language: Language? = null
)

data class Generation(

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)


data class Version(

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

data class Language(

  @field:SerializedName("name") val name: String? = null,

  @field:SerializedName("url") val url: String? = null
)

data class FlavorTextEntriesItem(

  @field:SerializedName("language") val language: Language? = null,

  @field:SerializedName("version") val version: Version? = null,

  @field:SerializedName("flavor_text") val flavorText: String? = null
)

data class Sprites(

  @field:SerializedName("back_shiny_female") val backShinyFemale: Any? = null,

  @field:SerializedName("back_female") val backFemale: Any? = null,

  @field:SerializedName("other") val other: Other? = null,

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("versions") val versions: Versions? = null,

  @field:SerializedName("front_female") val frontFemale: Any? = null,

  @field:SerializedName("back_shiny") val backShiny: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
) {
  fun toList(): List<Any?> {
    return listOf(
      this.frontDefault,
      this.frontShinyFemale,
      this.frontFemale,
      this.other?.dreamWorld?.frontDefault,
      this.other?.dreamWorld?.frontFemale,
      this.other?.officialArtwork?.frontDefault,
      this.other?.officialArtwork?.frontShiny,
      this.other?.home?.frontDefault,
      this.other?.home?.frontFemale,
      this.other?.home?.frontShiny,
      this.other?.home?.frontShinyFemale,
      this.other?.showdown?.frontDefault,
      this.other?.showdown?.frontShiny,
      this.other?.showdown?.frontFemale,
      this.other?.showdown?.frontShinyFemale,
    )
  }
}

data class Versions(

  @field:SerializedName("generation-i") val generationI: GenerationI? = null,

  @field:SerializedName("generation-ii") val generationIi: GenerationIi? = null,

  @field:SerializedName("generation-iii") val generationIii: GenerationIii? = null,

  @field:SerializedName("generation-iv") val generationIv: GenerationIv? = null,

  @field:SerializedName("generation-v") val generationV: GenerationV? = null,

  @field:SerializedName("generation-vi") val generationVi: GenerationVi? = null,

  @field:SerializedName("generation-vii") val generationVii: GenerationVii? = null,

  @field:SerializedName("generation-viii") val generationViii: GenerationViii? = null
)


data class Other(

  @field:SerializedName("dream_world") val dreamWorld: DreamWorld? = null,

  @field:SerializedName("showdown") val showdown: Showdown? = null,

  @field:SerializedName("official-artwork") val officialArtwork: OfficialArtwork? = null,

  @field:SerializedName("home") val home: Home? = null
)

data class DreamWorld(

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


data class OfficialArtwork(

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class Home(

  @field:SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_female") val frontFemale: Any? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)


//GEN 1
data class GenerationI(

  @field:SerializedName("yellow") val yellow: Yellow? = null,

  @field:SerializedName("red-blue") val redBlue: RedBlue? = null
)


data class Yellow(

  @field:SerializedName("front_gray") val frontGray: String? = null,

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("back_gray") val backGray: String? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null
)

data class RedBlue(

  @field:SerializedName("front_gray") val frontGray: String? = null,

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("back_gray") val backGray: String? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null
)

//GEN 2
data class GenerationIi(

  @field:SerializedName("gold") val gold: Gold? = null,

  @field:SerializedName("crystal") val crystal: Crystal? = null,

  @field:SerializedName("silver") val silver: Silver? = null
)

data class Gold(
  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("back_shiny") val backShiny: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class Crystal(

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("back_shiny") val backShiny: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class Silver(

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("back_shiny") val backShiny: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

//GEN 3
data class GenerationIii(

  @field:SerializedName("firered-leafgreen") val fireredLeafgreen: FireredLeafgreen? = null,

  @field:SerializedName("ruby-sapphire") val rubySapphire: RubySapphire? = null,

  @field:SerializedName("emerald") val emerald: Emerald? = null
)

data class FireredLeafgreen(

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("back_shiny") val backShiny: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class RubySapphire(

  @field:SerializedName("back_default") val backDefault: String? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("back_shiny") val backShiny: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

data class Emerald(

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)

//GEN 4
data class GenerationIv(

  @field:SerializedName("platinum") val platinum: Platinum? = null,

  @field:SerializedName("diamond-pearl") val diamondPearl: DiamondPearl? = null,

  @field:SerializedName("heartgold-soulsilver") val heartgoldSoulsilver: HeartgoldSoulsilver? = null
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

//GEN 5
data class GenerationV(

  @field:SerializedName("black-white") val blackWhite: BlackWhite? = null
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

//GEN 6
data class GenerationVi(

  @field:SerializedName("omegaruby-alphasapphire") val omegarubyAlphasapphire: OmegarubyAlphasapphire? = null,

  @field:SerializedName("x-y") val xY: XY? = null
)

data class OmegarubyAlphasapphire(

  @field:SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_female") val frontFemale: Any? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)


data class XY(

  @field:SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_female") val frontFemale: Any? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)


//GEN 7
data class GenerationVii(

  @field:SerializedName("icons") val icons: Icons? = null,

  @field:SerializedName("ultra-sun-ultra-moon") val ultraSunUltraMoon: UltraSunUltraMoon? = null
)


data class UltraSunUltraMoon(

  @field:SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_female") val frontFemale: Any? = null,

  @field:SerializedName("front_shiny") val frontShiny: String? = null
)


//GEN 8
data class GenerationViii(

  @field:SerializedName("icons") val icons: Icons? = null
)

data class Icons(

  @field:SerializedName("front_default") val frontDefault: String? = null,

  @field:SerializedName("front_female") val frontFemale: Any? = null
)