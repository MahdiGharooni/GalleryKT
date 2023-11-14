package ir.mahdi.gharooni.gallerykt.presentation.state

import ir.mahdi.gharooni.gallerykt.domain.model.Image
import ir.mahdi.gharooni.gallerykt.utils.Response
import kotlinx.coroutines.flow.Flow

class GetImagesState(
    private val isLoading: Boolean = false,
    private val image: List<Image> = emptyList(),
    private val message: String = "",
)
