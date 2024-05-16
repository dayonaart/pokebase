package id.dayona.pokebase.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.dayona.pokebase.MainModel.mainViewModel
import id.dayona.pokebase.ui.Tools.Giffy
import id.dayona.pokebase.ui.Tools.Loading

object Home {
  @Composable
  fun View(
    innerPad: PaddingValues, navController: NavController
  ) {
    val fieldFocus = LocalFocusManager.current
    if (mainViewModel.evolutionChainList.data.isEmpty()) {
      Loading(label = "Please wait while\ngetting Pokemon data ${mainViewModel.evolutionChainList.progress}%")
    } else {
      Column(
        modifier = Modifier
          .padding(innerPad)
          .padding(top = 10.dp)
      ) {
        OutlinedTextField(singleLine = true,
          shape = RoundedCornerShape(8.dp),
          trailingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
          },
          placeholder = { Text(text = "Search Pokemon") },
          modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
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

  @Composable
  private fun PokeList(navController: NavController) {
    LazyVerticalGrid(
      columns = GridCells.Adaptive(minSize = 120.dp)
    ) {
      items(mainViewModel.evolutionChainList.data.take(50).size) {
        val poke = mainViewModel.evolutionChainList.data[it]
        val sprite = poke?.sprites?.other?.showdown?.frontDefault
        Card(modifier = Modifier
          .padding(10.dp)
          .clickable {
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
}
