package ir.mahdi.gharooni.gallerykt.data.local.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ir.mahdi.gharooni.gallerykt.data.local.converters.Converters
import ir.mahdi.gharooni.gallerykt.data.local.dao.FavoriteDao
import ir.mahdi.gharooni.gallerykt.data.local.dao.GalleryDao
import ir.mahdi.gharooni.gallerykt.data.local.entity.FavoriteImageEntity
import ir.mahdi.gharooni.gallerykt.data.local.entity.ImageEntity
import ir.mahdi.gharooni.gallerykt.utils.TABLE_NAME_FAVORITE_IMAGES

@Database(
    entities = [ImageEntity::class, FavoriteImageEntity::class], version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class GalleryDataBase : RoomDatabase() {
    abstract fun dao(): GalleryDao
    abstract fun favoriteDao(): FavoriteDao
}


class Migration1to2 : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME_FAVORITE_IMAGES (" +
                "id TEXT PRIMARY KEY NOT NULL," +
                "description TEXT," +
                "likes INTEGER NOT NULL," +
                "user TEXT NOT NULL," +
                "url TEXT NOT NULL" +
                ")")
    }
}
