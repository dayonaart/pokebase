package id.dayona.pokebase.ui

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import id.dayona.pokebase.MainModel
import id.dayona.pokebase.R

object Tools {

  @Composable
  fun Loading(size: Int = 200, label: String? = null) {
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Giffy(url = R.raw.pokeball_loading, size = size.dp)
      if (label != null) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
          text = "Please wait while\ngetting Pokemon data ${MainModel.mainViewModel.evoList.progress}%",
          textAlign = TextAlign.Center
        )
      }
    }
  }
  @Composable
  fun Giffy(
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

}