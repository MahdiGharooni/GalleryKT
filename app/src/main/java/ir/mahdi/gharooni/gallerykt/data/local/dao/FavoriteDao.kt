package ir.mahdi.gharooni.gallerykt.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.mahdi.gharooni.gallerykt.data.local.entity.FavoriteImageEntity
import ir.mahdi.gharooni.gallerykt.utils.TABLE_NAME_FAVORITE_IMAGES

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteImage(image: FavoriteImageEntity)

    @Query("SELECT * FROM $TABLE_NAME_FAVORITE_IMAGES")
    suspend fun getAllFavoriteImages(): List<FavoriteImageEntity>

    @Query("DELETE FROM $TABLE_NAME_FAVORITE_IMAGES where id= :imageId")
    suspend fun deleteImage(imageId: String)


    @Delete
    suspend fun delete(image: FavoriteImageEntity)
}