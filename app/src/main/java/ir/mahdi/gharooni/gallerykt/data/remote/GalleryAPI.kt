package ir.mahdi.gharooni.gallerykt.data.remote

import ir.mahdi.gharooni.gallerykt.data.remote.dto.ImageDto
import ir.mahdi.gharooni.gallerykt.utils.TOKEN
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GalleryAPI {

    @Headers("Authorization: $TOKEN")
    @GET("photos")
    suspend fun getAllImages(
        @Query("page") page:Int,
        @Query("per_page") perPage:Int,
    ): List<ImageDto>

}