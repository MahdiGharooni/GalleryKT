package ir.mahdi.gharooni.gallerykt.data.remote.dto

import com.google.gson.annotations.SerializedName
import ir.mahdi.gharooni.gallerykt.domain.model.Link

data class LinkDto(
    val download: String,
    @SerializedName("download_location")
    val downloadLocation: String,
    val html: String,
    val self: String
)

fun LinkDto.toLink(): Link {
    return Link(
        download = download,
        self = self,
    )
}