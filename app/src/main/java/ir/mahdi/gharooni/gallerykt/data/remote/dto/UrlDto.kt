package ir.mahdi.gharooni.gallerykt.data.remote.dto

import com.google.gson.annotations.SerializedName
import ir.mahdi.gharooni.gallerykt.domain.model.Url

data class UrlDto(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    @SerializedName("small_s3")
    val smallS3: String,
    val thumb: String
)

fun UrlDto.toUrl(): Url {
    return Url(
        full = full,
        raw = raw,
        regular = regular,
    )
}