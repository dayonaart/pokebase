package id.dayona.pokeservices.network

import id.dayona.pokeservices.pokedata.evochain.EvolutionChain
import id.dayona.pokeservices.pokedata.evourl.EvolutionUrl
import id.dayona.pokeservices.pokedata.pokemon.Pokemon
import id.dayona.pokeservices.repositories.BASE_URL
import id.dayona.pokeservices.repositories.GET_EVOLUTION_CHAIN
import id.dayona.pokeservices.repositories.GET_EVOLUTION_CHAIN_URL
import id.dayona.pokeservices.repositories.GET_POKEMON
import id.dayona.pokeservices.repositories.PRIVATE_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Streaming
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

  fun privateRepo(): PrivateServices {
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
    return Retrofit.Builder().baseUrl(PRIVATE_BASE_URL).client(http).addConverterFactory(
      GsonConverterFactory.create()
    ).build().create(PrivateServices::class.java)
  }
}


internal interface Services {
  @Headers("Content-Type: application/json; charset=utf-8")
  @GET(GET_EVOLUTION_CHAIN_URL)
  suspend fun getEvolutionUrl(): Response<EvolutionUrl>

  @Headers("Content-Type: application/json; charset=utf-8")
  @GET(GET_EVOLUTION_CHAIN)
  suspend fun getEvolutionChain(@Path("id") id: String): Response<EvolutionChain>

  @Headers("Content-Type: application/json; charset=utf-8")
  @GET(GET_POKEMON)
  suspend fun getPokemon(@Path("id") id: String): Response<Pokemon>
}

internal interface PrivateServices {
  @Streaming
  @GET("uc?export=download&id=1r_4j5P6abMF-fekNWo6R7HFhzIiGmRDx")
  suspend fun downloadDatabase(): Response<ResponseBody?>
}