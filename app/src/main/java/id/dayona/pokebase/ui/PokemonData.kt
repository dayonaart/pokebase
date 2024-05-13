package id.dayona.pokebase.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.dayona.pokebase.MainViewModel
import id.dayona.pokebase.R
import id.dayona.pokebase.ui.Tools.Giffy
import id.dayona.pokeservices.data.Pokemon

object PokemonData {
  @Composable
  fun View(mainViewModel: MainViewModel, innerPad: PaddingValues) {
    Column(
      modifier = Modifier
        .padding(innerPad)
        .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      if (mainViewModel.pokeList.size < mainViewModel.getDefaultPokemon) {
        Giffy(url = R.raw.pokeball_loading)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
          text = "Please wait while\ngetting Pokemon data ${mainViewModel.pokeList.size}",
          textAlign = TextAlign.Center
        )
      } else {
        Table(mainViewModel.pokeList)
      }
    }
  }

  @Composable
  private fun Table(pokeList: List<Pokemon?>) {
    val cw1 = .2f
    val cw2 = .5f
    val cw3 = .3f
    LazyColumn(
      Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
      item {
        Row(Modifier.background(Color.Gray)) {
          TableCell(text = "id", weight = cw1)
          TableCell(text = "name", weight = cw2)
          TableCell(text = "type", weight = cw3)
        }
      }
      items(pokeList.size) {
        Row(Modifier.fillMaxWidth()) {
          TableCell(text = "${pokeList[it]?.id}", weight = cw1)
          TableCell(text = "${pokeList[it]?.name}", weight = cw2)
          TableCell(text = "${pokeList[it]?.types?.last()?.type?.name}", weight = cw3)
        }
      }
    }
  }

  @Composable
  private fun RowScope.TableCell(
    text: String, weight: Float
  ) {
    Text(
      text = text,
      Modifier
        .border(1.dp, Color.Black)
        .weight(weight)
        .padding(8.dp)
    )
  }

}