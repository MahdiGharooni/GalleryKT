package ir.mahdi.gharooni.gallerykt.domain.use_case

import ir.mahdi.gharooni.gallerykt.domain.model.Image
import ir.mahdi.gharooni.gallerykt.domain.repository.GalleryRepository
import ir.mahdi.gharooni.gallerykt.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: GalleryRepository
) {
    suspend operator fun invoke(page: Int, query: String): Flow<Response<List<Image>>> {
        return if (query.isEmpty()) {
            flow {
                Response.Error<List<Image>>(message = "The Query Is Empty!")
            }
        } else {
            repository.search(page, query)
        }
    }

}