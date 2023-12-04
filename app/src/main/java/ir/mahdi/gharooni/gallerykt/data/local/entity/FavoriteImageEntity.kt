package ir.mahdi.gharooni.gallerykt.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.mahdi.gharooni.gallerykt.domain.model.Image
import ir.mahdi.gharooni.gallerykt.domain.model.Url
import ir.mahdi.gharooni.gallerykt.domain.model.User
import ir.mahdi.gharooni.gallerykt.utils.TABLE_NAME_FAVORITE_IMAGES

@Entity(tableName = TABLE_NAME_FAVORITE_IMAGES)
data class FavoriteImageEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val description: String?,
    val likes: Int,
    val user: User,
    val url: Url,
)


fun FavoriteImageEntity.toImage(): Image {
    return Image(
        id = id,
        description = description,
        likes = likes,
        user = user,
        url = url,
    )
}