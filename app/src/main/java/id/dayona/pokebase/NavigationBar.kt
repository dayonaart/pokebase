package id.dayona.pokebase

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController


data class TabBarItem(
  val title: String, val selectedIcon: ImageVector? = null, val unselectedIcon: ImageVector? = null,
  val badgeAmount: Int? = null
)

@Composable
fun TabView(tabBarItems: List<TabBarItem>, navController: NavController, mainTab: Boolean = true) {
  var selectedTabIndex by rememberSaveable {
    mutableIntStateOf(0)
  }

  NavigationBar(windowInsets = WindowInsets.navigationBars) {
    tabBarItems.forEachIndexed { index, tabBarItem ->
      NavigationBarItem(selected = selectedTabIndex == index, onClick = {
        selectedTabIndex = index
        navController.navigate(tabBarItem.title) {
          if (!mainTab) return@navigate
          popUpTo(navController.graph.id) {
            saveState = true
          }
          launchSingleTop = true
          restoreState = true
        }
      }, icon = {
        TabBarTextView(title = tabBarItem.title)
      })
    }
  }
}

@Composable
private fun TabBarIconView(
  isSelected: Boolean,
  selectedIcon: ImageVector,
  unselectedIcon: ImageVector,
  title: String,
  badgeAmount: Int? = null
) {
  Text(text = title)

  BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
    Icon(
      imageVector = if (isSelected) {
        selectedIcon
      } else {
        unselectedIcon
      }, contentDescription = title
    )
  }
}

@Composable
private fun TabBarTextView(
  title: String,
) {
  Text(text = title)
}

@Composable
private fun TabBarBadgeView(count: Int? = null) {
  if (count != null) {
    Badge {
      Text(count.toString())
    }
  }
}