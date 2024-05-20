package id.dayona.pokebase.ui

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.dayona.pokebase.MainModel.mainViewModel
import id.dayona.pokebase.R
import id.dayona.pokebase.TabBarItem
import id.dayona.pokebase.TabView
import id.dayona.pokebase.atb
import id.dayona.pokebase.atbColor
import id.dayona.pokebase.pokemonColor
import id.dayona.pokebase.ui.Tools.Giffy
import id.dayona.pokebase.ui.Tools.Loading
import id.dayona.pokebase.ui.theme.Typography
import id.dayona.pokeservices.pokedata.evochain.EvolutionChain
import java.util.Locale

object PokeDetail {
  private val info = TabBarItem(
    title = "Info"
  )
  private val evolution = TabBarItem(
    title = "Evolution",

    )
  private val artWork = TabBarItem(
    title = "Art Work",

    )
  private val settingsTab = TabBarItem(
    title = "Settings",
  )

  @Composable
  fun View(innerPad: PaddingValues, id: String) {
    BodyView(innerPad, id)
  }

  @Composable
  private fun BodyView(innerPad: PaddingValues, id: String) {
    val tabEnt = listOf(info, evolution, artWork, settingsTab)
    val navController = rememberNavController()
    val evolutionChain = mainViewModel.evoList.data.find { it?.chain?.species?.name == id }
    Column(
      modifier = Modifier.padding(innerPad)
    ) {
      Box(
        modifier = Modifier
          .background(
            mainViewModel.getColor(
              evolutionChain?.chain?.species?.name ?: ""
            )?.name
              .pokemonColor()
              .copy(alpha = 0.5f)
          )
          .fillMaxWidth()
          .height(200.dp)
          .paint(
            painterResource(id = R.drawable.pokeball_gray),
            alpha = 0.3f,
          ), contentAlignment = Alignment.Center
      ) {
        Giffy(
          url = evolutionChain?.sprites?.other?.officialArtwork?.frontDefault ?: "", size = 150.dp
        )
      }
      Scaffold(topBar = { TabView(tabEnt, navController, tabHeight = 50) }) { ip ->
        NavHost(navController = navController, startDestination = tabEnt.first().title) {
          tabEnt.forEach { t ->
            composable(t.title) {
              when (t.title) {
                "Info" -> {
                  Info(ip, evolutionChain)
                }

                "Evolution" -> {
                  Column(
                    modifier = Modifier
                      .padding(innerPad)
                      .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                  ) {
                    Text(text = t.title)
                  }
                }

                "Art Work" -> {
                  val sprites = mainViewModel.pokemon?.sprites?.toList()?.filterNotNull()
                    ?.filter { f -> !(f as String).contains(".svg") }
                  Column(
                    modifier = Modifier
                      .padding(ip)
                      .fillMaxSize(),
                  ) {
                    LazyVerticalGrid(
                      columns = GridCells.Adaptive(minSize = 120.dp)
                    ) {
                      items(sprites?.size ?: 0) {
                        Giffy(
                          url = "${sprites?.get(it)}", size = 80.dp
                        )
                      }
                    }
                  }
                }

                "Settings" -> {
                  Column(
                    modifier = Modifier
                      .padding(innerPad)
                      .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                  ) {
                    Text(text = t.title)
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  data class BaseStatsData(val name: String, val atbAnim: State<Float>, val atbSize: Int)

  @SuppressLint("ResourceType")
  @Composable
  private fun Info(
    innerPad: PaddingValues,
    evolutionChain: EvolutionChain?,
  ) {
    val flText = mainViewModel.species?.flavorTextEntries?.filter { it?.language?.name == "en" }
    val baseStatus = mainViewModel.pokemon?.stats?.map {
      BaseStatsData(
        name = it?.stat?.name ?: "", atbAnim = animateFloatAsState(
          it?.baseStat?.toFloat() ?: 0f, label = "test", animationSpec = tween(
            durationMillis = 3000, delayMillis = 300, easing = LinearOutSlowInEasing
          )
        ), atbSize = it?.baseStat ?: 0
      )
    }
    Column(
      modifier = Modifier
        .padding(innerPad)
        .fillMaxSize()
        .padding(10.dp)
        .verticalScroll(rememberScrollState()),
    ) {
      Row {
        Text(text = "${
          evolutionChain?.chain?.species?.name?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
              Locale.getDefault()
            ) else it.toString()
          }
        }", style = Typography.titleLarge)
      }
      Spacer(modifier = Modifier.height(20.dp))
      if (!mainViewModel.getPokeLoading) {
        BaseStatus(baseStatus = baseStatus)
        Spacer(modifier = Modifier.height(20.dp))
        flText?.forEach {
          Column {
            Text(
              text = "Version ${it?.version?.name}",
              style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            HorizontalDivider()
            Text(text = "${it?.flavorText}")
            Spacer(modifier = Modifier.height(10.dp))
          }
        }
      } else {
        Loading(size = 100)
      }
    }
  }

  @Composable
  fun BaseStatus(baseStatus: List<BaseStatsData>?) {
    baseStatus?.forEachIndexed { _, state ->
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 3.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier.fillMaxWidth(0.3f)
        ) {
          Text(
            text = state.name.uppercase(),
            style = Typography.bodySmall.copy(fontWeight = FontWeight.Bold)
          )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Box(
          modifier = Modifier.fillMaxWidth(0.1f)
        ) {
          Text(
            text = "${state.atbSize}",
            style = Typography.bodySmall.copy(fontWeight = FontWeight.Bold)
          )
        }
        Box(
          Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(15.dp)
            .background(
              state.name
                .atbColor()
                .copy(alpha = 0.2f)
            ), contentAlignment = Alignment.CenterStart
        ) {
          Box(
            Modifier
              .clip(shape = RoundedCornerShape(10.dp))
              .fillMaxWidth(state.atbAnim.value.atb())
              .height(15.dp)
              .background(state.name.atbColor())
          )
        }
      }
    }
  }
}

