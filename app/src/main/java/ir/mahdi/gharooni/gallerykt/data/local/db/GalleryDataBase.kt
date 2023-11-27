package ir.mahdi.gharooni.gallerykt.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.mahdi.gharooni.gallerykt.data.local.converters.Converters
import ir.mahdi.gharooni.gallerykt.data.local.dao.GalleryDao
import ir.mahdi.gharooni.gallerykt.data.local.entity.ImageEntity

@Database(entities = [ImageEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class GalleryDataBase : RoomDatabase() {
    abstract fun dao(): GalleryDao
}