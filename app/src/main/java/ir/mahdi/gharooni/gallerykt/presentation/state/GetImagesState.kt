package ir.mahdi.gharooni.gallerykt.presentation.state

import ir.mahdi.gharooni.gallerykt.domain.model.Image

data class GetImagesState(
     var initialLoading: Boolean = false,
     var isLoading: Boolean = false,
     var images: List<Image> = emptyList(),
     var message: String = "",
     var endReached: Boolean = false,
     var page: Int = 1
)
