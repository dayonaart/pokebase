package id.dayona.pokebase.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import id.dayona.pokebase.MainModel.mainViewModel

object PokeTable {
  @Composable
  fun View(innerPad: PaddingValues) {
    Column(
      modifier = Modifier
        .padding(innerPad)
        .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Table()
    }
  }

  @Composable
  private fun Table() {
    val pokeList = mainViewModel.evoTable.data
    val cw1 = 15f
    val cw2 = 55f
    val cw3 = 35f
    LazyColumn(
      Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
      item {
        Row(Modifier.background(Color.Gray)) {
          TableCell(text = "id", weight = cw1)
          TableCell(text = "name", weight = cw2)
          TableCell(text = "is baby", weight = cw3)
        }
      }
      items(pokeList.size) {
        Row(Modifier.fillMaxWidth()) {
          TableCell(text = "${pokeList[it]?.id}", weight = cw1)
          TableCell(text = "${pokeList[it]?.chain?.species?.name}", weight = cw2)
          TableCell(
            text = "${pokeList[it]?.chain?.isBaby}",
            weight = cw3
          )
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