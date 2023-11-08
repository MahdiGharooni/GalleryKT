package ir.mahdi.gharooni.gallerykt.domain.repository

import ir.mahdi.gharooni.gallerykt.domain.model.Image
import ir.mahdi.gharooni.gallerykt.utils.Response
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {

    suspend fun getImages(): Flow<Response<List<Image>>>
}