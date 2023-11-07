package ir.mahdi.gharooni.gallerykt.data.remote

import ir.mahdi.gharooni.gallerykt.data.remote.dto.ImageDto
import ir.mahdi.gharooni.gallerykt.utils.TOKEN
import retrofit2.http.GET
import retrofit2.http.Headers

interface GalleryAPI {

    @Headers("Authorization: $TOKEN")
    @GET("photos")
    suspend fun getAllImages(): List<ImageDto>

}