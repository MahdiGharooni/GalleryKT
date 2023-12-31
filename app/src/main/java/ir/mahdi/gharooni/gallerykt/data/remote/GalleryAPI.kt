package ir.mahdi.gharooni.gallerykt.data.remote

import ir.mahdi.gharooni.gallerykt.data.remote.dto.ImageDto
import ir.mahdi.gharooni.gallerykt.data.remote.dto.SearchDto
import ir.mahdi.gharooni.gallerykt.utils.TOKEN
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GalleryAPI {

    @Headers("Authorization: $TOKEN")
    @GET("photos/random")
    suspend fun getAllImages(
        @Query("count") perPage: Int,
        @Query("orientation") orientation: String,
    ): List<ImageDto>


    @Headers("Authorization: $TOKEN")
    @GET("search/photos")
    suspend fun search(
        @Query("query") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("orientation") orientation: String,
    ): SearchDto

}