package ir.mahdi.gharooni.gallerykt.data.remote.dto

import com.google.gson.annotations.SerializedName
import ir.mahdi.gharooni.gallerykt.domain.model.Image

data class ImageDto(
    @SerializedName("alt_description")
    val altDescription: String?,
    @SerializedName("blur_hash")
    val blurHash: String,
    val color: String,
    @SerializedName("created_at")
    val createdAt: String,
    val description: String?,
    val height: Int,
    val id: String,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean,
    val likes: Int,
    @SerializedName("links")
    val linkDto: LinkDto,
    @SerializedName("promoted_at")
    val promotedAt: String,
    val slug: String,
    @SerializedName("sponsorship")
    val sponsorshipDto: SponsorshipDto?,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("urls")
    val urlDto: UrlDto,
    @SerializedName("user")
    val userDto: UserDto,
    val width: Int
)

fun ImageDto.toImage(): Image {
    return Image(
        id = id,
        altDescription = altDescription,
        color = color,
        createdAt = createdAt,
        description = description,
        likes = likes,
        link = linkDto.toLink(),
        slug = slug,
        urlDto = urlDto,
        userDto = userDto,
        url = urlDto.toUrl(),
    )
}