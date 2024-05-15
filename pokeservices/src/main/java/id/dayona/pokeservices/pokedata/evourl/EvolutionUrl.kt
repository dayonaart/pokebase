package id.dayona.pokeservices.pokedata.evourl

import com.google.gson.annotations.SerializedName

data class EvolutionUrl(

  @field:SerializedName("next")
  val next: String? = null,

  @field:SerializedName("previous")
  val previous: Any? = null,

  @field:SerializedName("count")
  val count: Int? = null,

  @field:SerializedName("results")
  val results: List<ResultsItem?>? = null
)

data class ResultsItem(

  @field:SerializedName("url")
  val url: String? = null
)
