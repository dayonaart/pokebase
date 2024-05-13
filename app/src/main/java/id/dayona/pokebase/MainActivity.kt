package id.dayona.pokebase

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import id.dayona.pokebase.ui.theme.PokeBaseTheme

class MainActivity : ComponentActivity() {
  private val mainViewModel by lazy { MainViewModel() }


  private val homeTab = TabBarItem(
    title = "Home", selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home
  )
  private val alertsTab = TabBarItem(
    title = "Alerts",
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
      val fieldFocus = LocalFocusManager.current
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
                Column(
                  modifier = Modifier.padding(innerPad)
                ) {
                  OutlinedTextField(
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    trailingIcon = {
                      Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                    },
                    placeholder = { Text(text = "Search Pokemon") },
                    modifier = Modifier.fillMaxWidth(),
                    value = mainViewModel.searchController,
                    onValueChange = mainViewModel::onSearchChange,
                    keyboardActions = KeyboardActions(onSearch = {
                      fieldFocus.clearFocus()
                    }),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
                  )
                  PokeList(
                    mainViewModel = mainViewModel
                  )
                }
              }
              composable(alertsTab.title) {
                Column(
                  modifier = Modifier
                    .padding(innerPad)
                    .fillMaxSize(),
                  horizontalAlignment = Alignment.CenterHorizontally,
                  verticalArrangement = Arrangement.Center
                ) {
                  if (mainViewModel.pokeList.size < mainViewModel.getDefaultPokemon) {
                    GifImage(url = R.raw.pokeball_loading)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                      text = "Please wait while\ngetting Pokemon data ${mainViewModel.pokeList.size}",
                      textAlign = TextAlign.Center
                    )
                  } else {
                    ElevatedButton(onClick = {
                      println(Utilities.readPokemon()?.size)
                    }) {
                      Text(text = "check db")
                    }
                  }
                }
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


@Composable
fun PokeList(
  mainViewModel: MainViewModel
) {
  if (mainViewModel.pokeList.isEmpty()) {
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      GifImage(url = R.raw.pokeball_loading)
    }
  } else {
    LazyVerticalGrid(
      columns = GridCells.Adaptive(minSize = 100.dp)
    ) {
      items(mainViewModel.pokeList.size) {
        val poke = mainViewModel.pokeList[it]
        Card(modifier = Modifier
          .padding(10.dp)
          .clickable {
            mainViewModel.cries(poke?.cries?.latest)
          }) {
          Column {
            Text(
              modifier = Modifier.padding(start = 5.dp), text = "${poke?.id ?: "Error"}"
            )
            GifImage(
              url = "${poke?.sprites?.other?.showdown?.frontDefault}", size = 80.dp
            )
            Text(
              modifier = Modifier.fillMaxWidth(),
              text = "${poke?.name}",
              textAlign = TextAlign.Center
            )
          }
        }
      }
    }
  }
}

@Composable
fun GifImage(
  url: Any, size: Dp = 200.dp
) {
  val context = LocalContext.current
  val imageLoader = ImageLoader.Builder(context).components {
    if (Build.VERSION.SDK_INT >= 28) {
      add(ImageDecoderDecoder.Factory())
    } else {
      add(GifDecoder.Factory())
    }
  }.build()
  Image(
    alignment = Alignment.Center,
    modifier = Modifier
      .fillMaxWidth()
      .size(size)
      .padding(10.dp),
    painter = rememberAsyncImagePainter(
      ImageRequest.Builder(context).data(data = url).apply(block = {
        size(Size.ORIGINAL)
      }).build(), imageLoader = imageLoader
    ),
    contentDescription = null
  )
}
