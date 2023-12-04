package ir.mahdi.gharooni.gallerykt.presentation.ui.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ir.mahdi.gharooni.gallerykt.ErrorText
import ir.mahdi.gharooni.gallerykt.LoadingView
import ir.mahdi.gharooni.gallerykt.presentation.state.FavoriteState
import ir.mahdi.gharooni.gallerykt.presentation.ui.home.ImageCard
import ir.mahdi.gharooni.gallerykt.presentation.view_model.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel()
) {


    val state by viewModel.state.collectAsState()

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text("Favorites")
            }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ), navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = "Back"
                    )
                }
            }, scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(
                rememberTopAppBarState()
            )
        )
    }) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding()
                )
        ) {
            if (state.isLoading) {
                LoadingView()
            } else if (state.images.isEmpty()) {
                ErrorText(state.message)
            } else {
                ImagesList(state, viewModel)
            }
        }
    }


}

@Composable
fun ImagesList(state: FavoriteState, viewModel: FavoriteViewModel) {
    val images = state.images
    val favState by   viewModel.state.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp)
    ) {
        items(images.size) {
            if (it == 0) {
                Spacer(modifier = Modifier.height(12.dp))
            }
            ImageCard(image = images[it])
            if (it == images.size - 1) {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

    }


}