package ir.mahdi.gharooni.gallerykt.presentation.state

import ir.mahdi.gharooni.gallerykt.domain.model.Image

class GetImagesState(
     val isLoading: Boolean = false,
     val images: List<Image> = emptyList(),
     val message: String = "",
)
