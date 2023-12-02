package ir.mahdi.gharooni.gallerykt.data.repository

import ir.mahdi.gharooni.gallerykt.data.local.dao.GalleryDao
import ir.mahdi.gharooni.gallerykt.data.local.entity.ImageEntity
import ir.mahdi.gharooni.gallerykt.data.local.entity.toImage
import ir.mahdi.gharooni.gallerykt.data.remote.GalleryAPI
import ir.mahdi.gharooni.gallerykt.data.remote.dto.toImage
import ir.mahdi.gharooni.gallerykt.domain.model.Image
import ir.mahdi.gharooni.gallerykt.domain.model.toImageEntity
import ir.mahdi.gharooni.gallerykt.domain.repository.GalleryRepository
import ir.mahdi.gharooni.gallerykt.utils.ORIENTATION
import ir.mahdi.gharooni.gallerykt.utils.PER_PAGE
import ir.mahdi.gharooni.gallerykt.utils.Response
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val api: GalleryAPI,
    private val dao: GalleryDao,
) : GalleryRepository {
    override suspend fun getImages(page: Int) = flow {
        try {
            emit(Response.Loading<List<Image>>())

            val images: List<Image>
            val cachedImages: List<ImageEntity> = dao.getImagesByPage(page)
            if (cachedImages.isNotEmpty()) {
                images = cachedImages.map {
                    it.toImage()
                }
            } else {
                images = api.getAllImages(page * PER_PAGE, ORIENTATION).map {
                    it.toImage()
                }
                dao.insertImages(images.map { it.toImageEntity(page) })
            }
            emit(Response.Success(images))
        } catch (e: IOException) {
            emit(Response.Error("Network Connection Problem"))
        } catch (e: HttpException) {
            emit(Response.Error("HTTP Response Problem"))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "An Error Occurred"))
        }

    }


    override suspend fun search(page: Int, query: String) = flow {
        try {
            emit(Response.Loading<List<Image>>())

            val images: List<Image> = api.search(query, PER_PAGE, page, ORIENTATION).results.map {
                it.toImage()
            }
            emit(Response.Success(images))
        } catch (e: IOException) {
            emit(Response.Error("Network Connection Problem"))
        } catch (e: HttpException) {
            emit(Response.Error("HTTP Response Problem"))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "An Error Occurred"))
        }

    }

}