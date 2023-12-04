package ir.mahdi.gharooni.gallerykt.utils

sealed class Screen(val route: String) {
    object ImagesListScreen : Screen("images_list_screen")
    object SearchScreen : Screen("search_screen")
    object FavoriteScreen : Screen("favorite_screen")
}