package id.dayona.pokebase.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.dayona.pokebase.MainModel.mainViewModel
import id.dayona.pokebase.ui.Tools.Giffy
import id.dayona.pokebase.ui.Tools.Loading
import id.dayona.pokebase.ui.theme.Typography

object Home {
  @Composable
  fun View(
    innerPad: PaddingValues, navController: NavController
  ) {
    Scaffold(modifier = Modifier.padding(innerPad), floatingActionButton = {
      Fab()
    }) { ip ->
      val fieldFocus = LocalFocusManager.current
      if (mainViewModel.evoList.data.isEmpty()) {
        Loading(label = "Please wait while\ngetting Pokemon data ${mainViewModel.evoList.progress}%")
      } else {
        Column(
          modifier = Modifier
            .padding(ip)
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
        ) {
          OutlinedTextField(
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            trailingIcon = {
              Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            },
            placeholder = { Text(text = "Search Pokemon") },
            modifier = Modifier
              .padding(bottom = 10.dp)
              .fillMaxWidth(),
            value = mainViewModel.searchController,
            onValueChange = mainViewModel::onSearchChange,
            keyboardActions = KeyboardActions(onSearch = {
              fieldFocus.clearFocus()
            }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
          )
          PokeList(navController)
        }
      }
    }
  }

  @Composable
  private fun PokeList(navController: NavController) {
    LazyVerticalGrid(
      columns = GridCells.Adaptive(minSize = 120.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      items(mainViewModel.getPokemonShow().size) {
        val poke = mainViewModel.evoList.data[it]
        val sprite = poke?.sprites?.other?.showdown?.frontDefault
        Card(modifier = Modifier.clickable {
          mainViewModel.getSpecies(poke?.chain?.species?.name ?: "")
          mainViewModel.getPokemon(poke?.chain?.species?.name ?: "")
          navController.navigate("poke_detail/${poke?.chain?.species?.name}")
        }) {
          Column {
            Text(
              modifier = Modifier.padding(start = 5.dp), text = "${poke?.id ?: "Error"}"
            )
            Giffy(
              url = "$sprite", size = 80.dp
            )
            Text(
              modifier = Modifier.fillMaxWidth(),
              text = "${poke?.chain?.species?.name}",
              textAlign = TextAlign.Center
            )
          }
        }
      }
    }
  }

  @Composable
  private fun Fab() {
    var sortExpanded by remember { mutableStateOf(false) }
    var viewExpanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
      ExtendedFloatingActionButton(
        modifier = Modifier.padding(top = 10.dp),
        onClick = { sortExpanded = !sortExpanded },
        icon = { Icon(Icons.Filled.Menu, "Sort") },
        text = { Text(text = "Sort") },
      )
      DropdownMenu(expanded = sortExpanded, onDismissRequest = { sortExpanded = false }) {
        DropdownMenuItem(text = { Text("Sort ASC") }, onClick = {
          mainViewModel.sortPokemon(true)
          sortExpanded = false
        })
        HorizontalDivider()
        DropdownMenuItem(text = { Text("Sort DES") }, onClick = {
          mainViewModel.sortPokemon(false)
          sortExpanded = false
        })
        HorizontalDivider()
        DropdownMenuItem(text = { Text("Reset") }, onClick = {
          mainViewModel.sortPokemon(null)
          sortExpanded = false
        })
        HorizontalDivider()
        DropdownMenuItem(text = { Text("View") }, onClick = {
          sortExpanded = false
          viewExpanded = !viewExpanded
        })
      }

      DropdownMenu(expanded = viewExpanded, onDismissRequest = { viewExpanded = false }) {
        DropdownMenuItem(text = { Text("1-100") }, onClick = {
          mainViewModel.pokeShow = 100
          viewExpanded = false
        })
        HorizontalDivider()
        DropdownMenuItem(text = { Text("1-200") }, onClick = {
          mainViewModel.pokeShow = 200
          viewExpanded = false
        })
        HorizontalDivider()
        DropdownMenuItem(text = { Text("1-300") }, onClick = {
          mainViewModel.pokeShow = 300
          viewExpanded = false
        })
      }
    }
  }
}
