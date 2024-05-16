package id.dayona.pokebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
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
    title = "Home"
  )
  private val alertsTab = TabBarItem(
    title = "Table"
  )
  private val settingsTab = TabBarItem(
    title = "Settings"
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    MainModel.mainViewModel = mainViewModel
    //MUST BE INIT HERE FOR DATABASE
//    this.getExternalFilesDirs("database")
    val tabBarItems = listOf(homeTab, alertsTab, settingsTab)
    setContent {
      val navController = rememberNavController()
      PokeBaseTheme(darkTheme = mainViewModel.darkMode) {
        Surface(
          modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
          Scaffold(topBar = {
          }, bottomBar = { TabView(tabBarItems, navController) }) { innerPad ->
            NavHost(navController = navController, startDestination = homeTab.title) {
              composable(homeTab.title) {
                Home.View(
                  innerPad = innerPad, navController = navController
                )
              }
              composable(alertsTab.title) {
                PokeTable.View(innerPad = innerPad)

              }
              composable(settingsTab.title) {
                Column(
                  modifier = Modifier
                    .padding(innerPad)
                    .fillMaxSize(),
                  horizontalAlignment = Alignment.CenterHorizontally,
                  verticalArrangement = Arrangement.Center
                ) {
                  ElevatedButton(onClick = {
                    mainViewModel.changeThemeMode()
                  }) {
                    Text(text = if (mainViewModel.darkMode) "Light Mode" else "Dark Mode")
                  }
                }
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


