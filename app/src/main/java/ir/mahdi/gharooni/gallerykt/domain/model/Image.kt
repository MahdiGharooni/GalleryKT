package ir.mahdi.gharooni.gallerykt.domain.model

import ir.mahdi.gharooni.gallerykt.data.local.entity.FavoriteImageEntity
import ir.mahdi.gharooni.gallerykt.data.local.entity.ImageEntity

data class Image(
    val id: String,
    val altDescription: String? = null,
    val color: String? = null,
    val createdAt: String? = null,
    val description: String? = null,
    val likes: Int,
    val link: Link? = null,
    val slug: String? = null,
    val user: User,
    val url: Url,
//    var isFavorite:Boolean = false,
)


fun Image.toImageEntity(page: Int): ImageEntity {
    return ImageEntity(
        page = page,
        id = id,
        description = description,
        likes = likes,
        user = user,
        url = url,
        )
}

fun Image.toFavoriteImageEntity(): FavoriteImageEntity {
    return FavoriteImageEntity(
        id = id,
        description = description,
        likes = likes,
        user = user,
        url = url,
        )
}