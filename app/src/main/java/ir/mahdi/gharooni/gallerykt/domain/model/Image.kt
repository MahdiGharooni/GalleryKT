package ir.mahdi.gharooni.gallerykt.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.mahdi.gharooni.gallerykt.data.remote.dto.UrlDto
import ir.mahdi.gharooni.gallerykt.data.remote.dto.UserDto

@Entity(tableName = "images")
data class Image(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val altDescription: String,
    val color: String,
    val createdAt: String,
    val description: String,
    val likes: Int,
    val link: Link,
    val slug: String,
    val urlDto: UrlDto,
    val userDto: UserDto,
    val url: Url,
)