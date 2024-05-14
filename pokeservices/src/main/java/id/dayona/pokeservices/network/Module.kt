package id.dayona.pokeservices.network

import id.dayona.pokeservices.data.EvoChain
import id.dayona.pokeservices.data.PokemonData
import id.dayona.pokeservices.data.SpeciesData
import id.dayona.pokeservices.repositories.BASE_URL
import id.dayona.pokeservices.repositories.GET_EVO_CHAIN
import id.dayona.pokeservices.repositories.GET_POKEMON
import id.dayona.pokeservices.repositories.GET_SPECIES
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

internal object Module {
  fun repo(): Services {
    val timeoutInSeconds = 30000L
    val logging = HttpLoggingInterceptor()
//    when (logLevel) {
//      LogLevel.NONE -> logging.setLevel(HttpLoggingInterceptor.Level.NONE)
//      LogLevel.HEADER -> logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
//      LogLevel.BODY -> logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//      LogLevel.RESPONSE -> logging.setLevel(HttpLoggingInterceptor.Level.NONE)
//      LogLevel.FULL -> logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//    }
    val http = OkHttpClient().newBuilder().addInterceptor(logging)
      .connectTimeout(timeoutInSeconds, TimeUnit.SECONDS)
      .readTimeout(timeoutInSeconds, TimeUnit.SECONDS)
      .writeTimeout(timeoutInSeconds, TimeUnit.SECONDS).build()
    return Retrofit.Builder().baseUrl(BASE_URL).client(http).addConverterFactory(
      GsonConverterFactory.create()
    ).build().create(Services::class.java)
  }
}


internal interface Services {
  @Headers("Content-Type: application/json; charset=utf-8")
  @GET(GET_POKEMON)
  suspend fun getPokemon(@Path("id") id: String): Response<PokemonData>
  @Headers("Content-Type: application/json; charset=utf-8")
  @GET(GET_SPECIES)
  suspend fun getSpecies(@Path("id") id: String): Response<SpeciesData>

  @Headers("Content-Type: application/json; charset=utf-8")
  @GET(GET_EVO_CHAIN)
  suspend fun getEvoChain(@Path("id") id: String): Response<EvoChain>
}