package id.dayona.pokebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.dayona.pokebase.ui.Home
import id.dayona.pokebase.ui.PokeDetail
import id.dayona.pokebase.ui.PokeTable
import id.dayona.pokebase.ui.theme.PokeBaseTheme

class MainActivity : ComponentActivity() {
  private val mainViewModel by lazy { MainViewModel() }

  private val homeTab = TabBarItem(
    title = "Home", selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home
  )
  private val alertsTab = TabBarItem(
    title = "Table",
    selectedIcon = Icons.Filled.Menu,
    unselectedIcon = Icons.Outlined.Menu,
  )
  private val settingsTab = TabBarItem(
    title = "Settings",
    selectedIcon = Icons.Filled.Settings,
    unselectedIcon = Icons.Outlined.Settings
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val tabBarItems = listOf(homeTab, alertsTab, settingsTab)
    PokeDetail.mainViewModel = mainViewModel
    setContent {
      val navController = rememberNavController()
      PokeBaseTheme {
        Surface(
          modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
          Scaffold(topBar = {
          }, bottomBar = { TabView(tabBarItems, navController) }) { innerPad ->
            NavHost(navController = navController, startDestination = homeTab.title) {
              composable(homeTab.title) {
                Home.View(
                  mainViewModel = mainViewModel, innerPad = innerPad, navController = navController
                )
              }
              composable(alertsTab.title) {
                PokeTable.View(mainViewModel = mainViewModel, innerPad = innerPad)
              }
              composable(settingsTab.title) {
                Text(settingsTab.title)
              }
              composable(
                "poke_detail/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType })
              ) {
                PokeDetail.View(
                  innerPad = innerPad,
                  it.arguments?.getString("id") ?: ""
                )
              }
            }
          }
        }
      }
    }
  }
}
