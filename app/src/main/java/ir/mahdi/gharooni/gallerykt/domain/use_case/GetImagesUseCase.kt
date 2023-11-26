package ir.mahdi.gharooni.gallerykt.domain.use_case

import ir.mahdi.gharooni.gallerykt.domain.model.Image
import ir.mahdi.gharooni.gallerykt.domain.repository.GalleryRepository
import ir.mahdi.gharooni.gallerykt.utils.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val repository: GalleryRepository
) {
    suspend operator fun invoke(page: Int): Flow<Response<List<Image>>> {
        return repository.getImages(page)
    }

}