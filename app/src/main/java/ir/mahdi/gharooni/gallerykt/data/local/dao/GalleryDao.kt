package ir.mahdi.gharooni.gallerykt.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.mahdi.gharooni.gallerykt.data.local.entity.ImageEntity
import ir.mahdi.gharooni.gallerykt.utils.TABLE_NAME_IMAGES

@Dao
interface GalleryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<ImageEntity>)

    @Query("SELECT * FROM $TABLE_NAME_IMAGES WHERE page = :pageId")
    suspend fun getImagesByPage(pageId: Int): List<ImageEntity>
}