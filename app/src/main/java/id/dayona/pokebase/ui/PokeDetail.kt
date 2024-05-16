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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.dayona.pokebase.MainModel.mainViewModel
import id.dayona.pokebase.TabBarItem
import id.dayona.pokebase.TabView
import id.dayona.pokebase.atb
import id.dayona.pokebase.atbColor
import id.dayona.pokebase.shortAtbName
import id.dayona.pokebase.ui.Tools.Giffy
import id.dayona.pokebase.ui.Tools.Loading
import id.dayona.pokebase.ui.theme.Typography
import id.dayona.pokeservices.pokedata.evochain.EvolutionChain
import java.util.Locale

object PokeDetail {
  private val baseStats = TabBarItem(
    title = "Base Stats"
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
    ScaffoldView(innerPad, id)
  }
  @Composable
  private fun ScaffoldView(innerPad: PaddingValues, id: String) {
    val tabEnt = listOf(baseStats, evolution, artWork, settingsTab)
    val navController = rememberNavController()
    val evolutionChain =
      mainViewModel.evolutionChainList.data.find { it?.chain?.species?.name == id }
    Column(
      modifier = Modifier
        .padding(innerPad)
    ) {
      Giffy(url = evolutionChain?.sprites?.other?.officialArtwork?.frontDefault ?: "")
      Scaffold(topBar = { TabView(tabEnt, navController, tabHeight = 50) }) { ip ->
        NavHost(navController = navController, startDestination = tabEnt.first().title) {
          tabEnt.forEach { t ->
            composable(t.title) {
              when (t.title) {
                "Base Stats" -> {
                  BaseStats(ip, evolutionChain)
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
                  val sprites = mainViewModel.pokemon?.sprites?.toList()
                    ?.filterNotNull()?.filter { f -> !(f as String).contains(".svg") }
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
  private fun BaseStats(
    innerPad: PaddingValues,
    evolutionChain: EvolutionChain?,
  ) {
    val baseStats = mainViewModel.pokemon?.stats?.map {
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
        baseStats?.forEachIndexed { _, state ->
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(bottom = 3.dp)
          ) {
            Box(
              modifier = Modifier.width(60.dp)
            ) {
              Text(
                text = "${state.name.shortAtbName()}"
              )
            }
            Box(
              modifier = Modifier.width(30.dp)
            ) {
              Text(
                text = "${state.atbSize}"
              )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box(
              Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(20.dp)
                .background(
                  state.name
                    .atbColor()
                    .copy(alpha = 0.2f)
                )
            ) {
              Box(
                Modifier
                  .clip(shape = RoundedCornerShape(10.dp))
                  .fillMaxWidth(state.atbAnim.value.atb())
                  .height(20.dp)
                  .background(state.name.atbColor())
              )
            }
          }
        }
      } else {
        Loading(size = 100)
      }
    }
  }
}

