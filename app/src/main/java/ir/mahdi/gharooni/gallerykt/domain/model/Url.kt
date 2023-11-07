package ir.mahdi.gharooni.gallerykt.domain.model

import com.google.gson.annotations.SerializedName

data class Url(
    val full: String,
    val raw: String,
    val regular: String,
)