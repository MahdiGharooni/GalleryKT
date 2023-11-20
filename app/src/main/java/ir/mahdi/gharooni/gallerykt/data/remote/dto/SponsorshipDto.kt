package ir.mahdi.gharooni.gallerykt.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SponsorshipDto(
    @SerializedName("impression_urls")
    val impressionUrls: List<String>,
    @SerializedName("sponsor")
    val sponsorDto: SponsorDto,
    val tagline: String,
    @SerializedName("tagline_url")
    val taglineUrl: String
)