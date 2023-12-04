package ir.mahdi.gharooni.gallerykt.data.repository

import ir.mahdi.gharooni.gallerykt.data.local.dao.FavoriteDao
import ir.mahdi.gharooni.gallerykt.data.local.entity.toImage
import ir.mahdi.gharooni.gallerykt.domain.model.Image
import ir.mahdi.gharooni.gallerykt.domain.model.toFavoriteImageEntity
import ir.mahdi.gharooni.gallerykt.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao
) : FavoriteRepository {
    override suspend fun insertFavoriteImage(image: Image) {
        dao.insertFavoriteImage(image = image.toFavoriteImageEntity())
    }

    override suspend fun getFavoriteImages(): List<Image> {
        return dao.getAllFavoriteImages().map {
            it.toImage()
        }
    }

    override suspend fun deleteFavoriteImage(image: Image) {
        dao.delete(image = image.toFavoriteImageEntity())
    }
}