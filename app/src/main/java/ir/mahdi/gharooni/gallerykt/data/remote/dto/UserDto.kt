package ir.mahdi.gharooni.gallerykt.data.remote.dto

import com.google.gson.annotations.SerializedName
import ir.mahdi.gharooni.gallerykt.domain.model.User

data class UserDto(
    @SerializedName("accepted_tos")
    val acceptedTos: Boolean,
    val bio: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("for_hire")
    val forHire: Boolean,
    @SerializedName("accepted_tos")
    val id: String,
    @SerializedName("instagram_username")
    val instagramUsername: String,
    @SerializedName("last_name")
    val lastName: Any,
    val links: SponsorLinkDto,
    val location: String,
    val name: String,
    @SerializedName("portfolio_url")
    val portfolioUrl: String,
    @SerializedName("profile_image")
    val profileImage: ProfileImageDto,
    val social: SocialDto,
    @SerializedName("total_collections")
    val totalCollections: Int,
    @SerializedName("total_likes")
    val totalLikes: Int,
    @SerializedName("total_photos")
    val totalPhotos: Int,
    @SerializedName("twitter_username")
    val twitterUsername: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val username: String
)

fun UserDto.toUser(): User {
    return User(
        id = id,
        acceptedTos = acceptedTos,
        bio = bio,
        firstName = firstName,
        lastName = lastName,
        name = name,
        profileImage = profileImage.toProfileImage(),
        username = username,
    )
}