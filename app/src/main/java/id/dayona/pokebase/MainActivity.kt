package id.dayona.pokebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import id.dayona.pokebase.ui.Home
import id.dayona.pokebase.ui.PokemonData
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
    Utilities.context = this
    val tabBarItems = listOf(homeTab, alertsTab, settingsTab)
    setContent {
      val navController = rememberNavController()
      PokeBaseTheme {
        Surface(
          modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
          Scaffold(modifier = Modifier.padding(10.dp), topBar = {
            Card(
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp)
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)
              ) {
                AsyncImage(
                  model = R.drawable.pokeball,
                  contentDescription = "",
                  modifier = Modifier.size(45.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                  text = "Pokemon Database",
                  style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                )
              }
            }
          }, bottomBar = { TabView(tabBarItems, navController) }) { innerPad ->
            NavHost(navController = navController, startDestination = homeTab.title) {
              composable(homeTab.title) {
                Home.View(mainViewModel = mainViewModel, innerPad = innerPad)
              }
              composable(alertsTab.title) {
                PokemonData.View(mainViewModel = mainViewModel, innerPad = innerPad)
              }
              composable(settingsTab.title) {
                Text(settingsTab.title)
              }
            }
          }
        }
      }
    }
  }
}
