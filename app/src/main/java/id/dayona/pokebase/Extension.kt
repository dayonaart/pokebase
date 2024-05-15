package id.dayona.pokebase

import androidx.compose.ui.graphics.Color

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
      return Color.Green.copy(alpha = 0.5f)
    }

    "attack" -> {
      return Color.Red.copy(alpha = 0.5f)
    }

    "defense" -> {
      return Color.Blue.copy(alpha = 0.5f)
    }

    "special-attack" -> {
      return Color.Yellow.copy(alpha = 0.5f)
    }

    "special-defense" -> {
      return Color.Magenta.copy(alpha = 0.5f)
    }

    "speed" -> {
      return Color.Cyan.copy(alpha = 0.5f)
    }

    else -> {
      return Color.Black
    }
  }
}