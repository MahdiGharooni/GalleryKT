package ir.mahdi.gharooni.gallerykt.presentation.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ir.mahdi.gharooni.gallerykt.R
import ir.mahdi.gharooni.gallerykt.presentation.view_model.FavoriteViewModel
import ir.mahdi.gharooni.gallerykt.domain.model.Image as galleryImage

@Composable
fun ImageCard(image: galleryImage, favViewModel: FavoriteViewModel = hiltViewModel()) {


    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .height(200.dp)
            .padding(bottom = 4.dp, top = 4.dp),
    ) {
        Box {
            AsyncImage(
                model = image.url.regular,
                contentDescription = image.description,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.placeholder),
            )
            Footer(image, favViewModel)
        }
    }
}

@Composable
fun Footer(image: galleryImage, favViewModel: FavoriteViewModel) {
    val state by favViewModel.state. collectAsState()
   val isFav :Boolean =  state.images.contains(image)
    Surface(
        modifier = Modifier
            .offset(x = 0.dp, y = 160.dp)
            .height(48.dp)
            .fillMaxWidth(),
        color = Color.Transparent.copy(alpha = 0.7f)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Row {
                AsyncImage(
                    model = image.user.profileImage.small,
                    contentDescription = image.user.name,
                    modifier = Modifier
                        .size(21.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.FillWidth,
                    placeholder = painterResource(R.drawable.person),
                    error = painterResource(R.drawable.person),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = image.user.name)
            }
            Row {
                Text(text = "${image.likes}")
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "",
                    tint = if (isFav) {
                        Color.Red
                    } else {
                        Color.White
                    },
                    modifier = Modifier.clickable {
                        if (isFav) {
                            favViewModel.deleteFavoriteImage(image)
                        } else {
                            favViewModel. insertFavoriteImage(image)
                        }
                    }
                )


            }
        }
    }


}
