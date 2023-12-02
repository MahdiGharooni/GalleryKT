package ir.mahdi.gharooni.gallerykt.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SearchDto (
    @SerializedName("total_pages")
    val totalPages: Int,
    val total: Int,
    val results : List<ImageDto>
)