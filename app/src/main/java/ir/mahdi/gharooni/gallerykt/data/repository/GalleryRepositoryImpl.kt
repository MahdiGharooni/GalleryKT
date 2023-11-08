package ir.mahdi.gharooni.gallerykt.data.repository

import ir.mahdi.gharooni.gallerykt.data.remote.GalleryAPI
import ir.mahdi.gharooni.gallerykt.data.remote.dto.toImage
import ir.mahdi.gharooni.gallerykt.domain.model.Image
import ir.mahdi.gharooni.gallerykt.domain.repository.GalleryRepository
import ir.mahdi.gharooni.gallerykt.utils.Response
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val api: GalleryAPI
) : GalleryRepository {
    override suspend fun getImages() = flow {
        emit(Response.Loading<List<Image>>())
        try {
            val images: List<Image> = api.getAllImages().map {
                it.toImage()
            }

            emit(Response.Success(images))
        } catch (e: IOException) {
            emit(Response.Error("IOException"))
        } catch (e: HttpException) {
            emit(Response.Error("HttpException"))
        }

    }

}