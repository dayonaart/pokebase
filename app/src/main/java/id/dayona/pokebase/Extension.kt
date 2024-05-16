package id.dayona.pokebase

import androidx.compose.ui.graphics.Color
import id.dayona.pokebase.ui.theme.Black
import id.dayona.pokebase.ui.theme.Cyan
import id.dayona.pokebase.ui.theme.Lime
import id.dayona.pokebase.ui.theme.Olive
import id.dayona.pokebase.ui.theme.Orange
import id.dayona.pokebase.ui.theme.Red
import id.dayona.pokebase.ui.theme.Yellow

fun String?.shortAtbName(): String? {
  return this?.replace("attack", "Atk")?.replace("defense", "Def")?.replace("special", "Sp")
}

fun Float.atb(): Float {
  return this / 300
  if (this.compareTo(100) > 0) {
    if (this.compareTo(200) > 0) return this / 300
    return this / 200
  } else {
    return this / 100
  }
}

fun String.atbColor(): Color {
  when (this) {
    "hp" -> {
      return Lime
    }

    "attack" -> {
      return Red
    }

    "defense" -> {
      return Olive
    }

    "special-attack" -> {
      return Cyan
    }

    "special-defense" -> {
      return Orange
    }

    "speed" -> {
      return Yellow
    }

    else -> {
      return Black
    }
  }
}