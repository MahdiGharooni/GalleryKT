package ir.mahdi.gharooni.gallerykt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import ir.mahdi.gharooni.gallerykt.domain.model.Image
import ir.mahdi.gharooni.gallerykt.presentation.state.GetImagesState
import ir.mahdi.gharooni.gallerykt.presentation.ui.home.ImageCard
import ir.mahdi.gharooni.gallerykt.presentation.view_model.GetImagesViewModel
import ir.mahdi.gharooni.gallerykt.utils.ui.theme.GalleryKTTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryKTTheme {
                val viewModel = viewModel<GetImagesViewModel>()
                val state: State<GetImagesState> = viewModel.state
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    if (state.value.isLoading) {
                        LoadingView()
                    } else if (state.value.images.isEmpty()) {
                        ErrorText(state.value.message)
                    } else {
                        ImagesList(state.value.images)
                    }
                }
            }
        }
    }
}


@Composable
fun LoadingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
        )
    }
}

@Composable
fun ErrorText(message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun ImagesList(images: List<Image>) {
    LazyColumn(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        items(images.size) {
            if (it == 0) {
                Spacer(modifier = Modifier.height(24.dp))
            }
            ImageCard(image = images[it])
            if (it == images.size - 1) {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

    }


}