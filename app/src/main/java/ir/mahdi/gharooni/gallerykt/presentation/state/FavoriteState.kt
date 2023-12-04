package ir.mahdi.gharooni.gallerykt.presentation.state

import ir.mahdi.gharooni.gallerykt.domain.model.Image

data class FavoriteState(
    var isLoading: Boolean = false,
    var images: List<Image> = emptyList(),
    var message: String = "",
)
