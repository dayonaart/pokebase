package id.dayona.pokebase.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.dayona.pokebase.MainViewModel
import id.dayona.pokebase.TabBarItem
import id.dayona.pokebase.TabView
import id.dayona.pokebase.ui.Tools.Giffy

object PokeDetail {

  private val homeTab = TabBarItem(
    title = "Home"
  )
  private val alertsTab = TabBarItem(
    title = "Table",

    )
  private val settingsTab = TabBarItem(
    title = "Settings",
  )

  @Composable
  fun View(mainViewModel: MainViewModel, innerPad: PaddingValues, id: String) {
    ScaffoldView(innerPad, mainViewModel, id)
  }

  @Composable
  private fun ScaffoldView(innerPad: PaddingValues, mainViewModel: MainViewModel, id: String) {
    val tabEnt = listOf(homeTab, alertsTab, settingsTab)
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.padding(innerPad),
      topBar = { TabView(tabEnt, navController) }) { ip ->
      NavHost(navController = navController, startDestination = tabEnt.first().title) {
        tabEnt.forEach { t ->
          composable(t.title) {
            when (t.title) {
              "Home" -> {
                Home(ip, mainViewModel, id)
              }

              "Table" -> {
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

              "Setting" -> {
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

  @Composable
  private fun Home(innerPad: PaddingValues, mainViewModel: MainViewModel, id: String) {
    val pokemon = mainViewModel.evoChain.find { it?.chain?.speciesData?.name == id }
    Column(
      modifier = Modifier
        .padding(innerPad)
        .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      if (mainViewModel.pokemonData != null) {
        Giffy(
          url = mainViewModel.pokemonData?.sprites?.other?.officialArtwork?.frontDefault ?: ""
        )
      } else {
        Giffy(
          url = pokemon?.sprites?.other?.officialArtwork?.frontDefault ?: ""
        )
      }
      Row {
        pokemon?.chain?.evolvesTo?.forEach {
          ElevatedButton(onClick = {
            mainViewModel.getPokemon(it?.speciesData?.name ?: "")
          }) {
            Text(text = "Evolve to\n${it?.speciesData?.name}")
          }
        }
      }
    }
  }
}

