package ir.mahdi.gharooni.gallerykt.presentation.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ir.mahdi.gharooni.gallerykt.ErrorText
import ir.mahdi.gharooni.gallerykt.LoadingView
import ir.mahdi.gharooni.gallerykt.presentation.state.GetImagesState
import ir.mahdi.gharooni.gallerykt.presentation.ui.home.ImageCard
import ir.mahdi.gharooni.gallerykt.presentation.view_model.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val state: State<GetImagesState> = remember { viewModel.state }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text("")
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(
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
            SearchTextField()
            if (state.value.initialLoading) {
                LoadingView()
            } else if (state.value.images.isEmpty()) {
                ErrorText(state.value.message)
            } else {
                ImagesList(state.value, viewModel)
            }
        }
    }


}

@Composable
fun ImagesList(state: GetImagesState, viewModel: SearchViewModel) {
    val images = state.images
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 84.dp, start = 24.dp, end = 24.dp)
    ) {
        items(images.size) {
            if (it >= (images.size - 1) && !state.endReached && !state.isLoading) {
                val focusManager = LocalFocusManager.current
                focusManager.clearFocus()
                viewModel.loadNextPage()
            }
            if (it == 0) {
                Spacer(modifier = Modifier.height(12.dp))
            }
            ImageCard(image = images[it])
            if (it == images.size - 1) {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
        item {
            if (state.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }


}