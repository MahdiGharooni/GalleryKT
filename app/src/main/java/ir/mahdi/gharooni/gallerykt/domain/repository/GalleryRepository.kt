package ir.mahdi.gharooni.gallerykt.domain.repository

import ir.mahdi.gharooni.gallerykt.domain.model.Image
import ir.mahdi.gharooni.gallerykt.utils.Response
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {

    suspend fun getImages(page: Int): Flow<Response<List<Image>>>
    suspend fun search(page: Int, query: String): Flow<Response<List<Image>>>
}