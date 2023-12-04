package ir.mahdi.gharooni.gallerykt.domain.repository

import ir.mahdi.gharooni.gallerykt.domain.model.Image

interface FavoriteRepository {
    suspend fun insertFavoriteImage(image: Image)
    suspend fun getFavoriteImages(): List<Image>
    suspend fun deleteFavoriteImage(image: Image)

}