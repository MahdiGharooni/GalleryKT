package ir.mahdi.gharooni.gallerykt.data.remote.dto

import ir.mahdi.gharooni.gallerykt.domain.model.ProfileImage

data class ProfileImageDto(
    val large: String,
    val medium: String,
    val small: String
)

fun ProfileImageDto.toProfileImage(): ProfileImage {
    return ProfileImage(
        large = large,
        medium = medium,
        small = small,
    )
}