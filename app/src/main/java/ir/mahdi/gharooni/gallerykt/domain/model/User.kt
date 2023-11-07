package ir.mahdi.gharooni.gallerykt.domain.model


data class User(
    val id: String,
    val acceptedTos: Boolean,
    val bio: String,
    val firstName: String,
    val lastName: Any,
    val name: String,
    val profileImage: ProfileImage,
    val username: String
)