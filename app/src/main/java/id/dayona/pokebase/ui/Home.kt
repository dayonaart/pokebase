package id.dayona.pokebase.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.dayona.pokebase.MainViewModel
import id.dayona.pokebase.R
import id.dayona.pokebase.ui.Tools.Giffy

object Home {
  @Composable
  fun View(mainViewModel: MainViewModel, innerPad: PaddingValues) {
    val fieldFocus = LocalFocusManager.current
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


  @Composable
  private fun PokeList(
    mainViewModel: MainViewModel
  ) {
    if (mainViewModel.pokeList.isEmpty()) {
      Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {
        Giffy(url = R.raw.pokeball_loading)
      }
    } else {
      LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp)
      ) {
        items(mainViewModel.pokeList.take(50).size) {
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
              Giffy(
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

}
