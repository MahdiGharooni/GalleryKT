package ir.mahdi.gharooni.gallerykt.domain.use_case

import ir.mahdi.gharooni.gallerykt.domain.model.Image
import ir.mahdi.gharooni.gallerykt.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend fun insertImage(image: Image) {
        repository.insertFavoriteImage(image)
    }

     suspend  fun getFavoriteImages(): List<Image> {
        return repository.getFavoriteImages()
    }

    suspend fun deleteImage(image: Image) {
        repository.deleteFavoriteImage(image)
    }
}