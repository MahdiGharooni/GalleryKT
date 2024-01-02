package ir.mahdi.gharooni.gallerykt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.mahdi.gharooni.gallerykt.presentation.state.FavoriteState
import ir.mahdi.gharooni.gallerykt.presentation.state.GetImagesState
import ir.mahdi.gharooni.gallerykt.presentation.ui.favorite.FavoriteScreen
import ir.mahdi.gharooni.gallerykt.presentation.ui.home.ImageCard
import ir.mahdi.gharooni.gallerykt.presentation.ui.search.SearchScreen
import ir.mahdi.gharooni.gallerykt.presentation.view_model.FavoriteViewModel
import ir.mahdi.gharooni.gallerykt.presentation.view_model.GetImagesViewModel
import ir.mahdi.gharooni.gallerykt.utils.Screen
import ir.mahdi.gharooni.gallerykt.utils.ui.theme.GalleryKTTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryKTTheme {
                val viewModel : GetImagesViewModel= hiltViewModel()
                val state: State<GetImagesState> = remember { viewModel.state }
                val favViewModel : FavoriteViewModel= hiltViewModel()


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ImagesListScreen.route
                    ) {
                        composable(
                            route = Screen.ImagesListScreen.route
                        ) {
                            Scaffold(
                                topBar = {
                                    TopAppBar(
                                        title = {
                                            Text(
                                                "Gallery",
                                            )
                                        },
                                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                                            titleContentColor = MaterialTheme.colorScheme.primary,
                                        ),
                                        actions = {
                                            IconButton(onClick = {
                                                navController.navigate(
                                                    Screen.FavoriteScreen.route
                                                )
                                            }) {
                                                Icon(
                                                    imageVector = Icons.Filled.Favorite,
                                                    contentDescription = "Favorite"
                                                )
                                            }
                                            IconButton(onClick = {
                                                navController.navigate(
                                                    Screen.SearchScreen.route
                                                )
                                            }) {
                                                Icon(
                                                    imageVector = Icons.Filled.Search,
                                                    contentDescription = "Search"
                                                )
                                            }
                                        },
                                        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(
                                            rememberTopAppBarState()
                                        )
                                    )
                                },
                            ) {
                                if (state.value.initialLoading) {
                                    LoadingView()
                                } else if (state.value.images.isEmpty()) {
                                    ErrorText(state.value.message)
                                } else {
                                    ImagesList(state.value, viewModel, favViewModel , it)
                                }
                            }
                        }
                        composable(
                            route = Screen.SearchScreen.route,
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(500)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(500)
                                )
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(500),

                                    )
                            },
                        ) {
                            SearchScreen(
                                navController = navController
                            )
                        }
                        composable(
                            route = Screen.FavoriteScreen.route,
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(500)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(500)
                                )
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(500),

                                    )
                            },
                        ) {
                            FavoriteScreen(
                                navController = navController
                            )
                        }
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
fun ImagesList(
    state: GetImagesState,
    viewModel: GetImagesViewModel,
    favViewModel: FavoriteViewModel,
    padding: PaddingValues
) {
    val images = state.images
    val favState by   favViewModel.state.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = padding.calculateTopPadding(), start = 24.dp, end = 24.dp)
    ) {
        items(images.size) {
            if (it >= (images.size - 1) && !state.endReached && !state.isLoading) {
                viewModel.loadNextItems()
            }
            if (it == 0) {
                Spacer(modifier = Modifier.height(24.dp))
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