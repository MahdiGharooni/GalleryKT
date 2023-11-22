package ir.mahdi.gharooni.gallerykt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.mahdi.gharooni.gallerykt.data.remote.GalleryAPI
import ir.mahdi.gharooni.gallerykt.data.repository.GalleryRepositoryImpl
import ir.mahdi.gharooni.gallerykt.domain.repository.GalleryRepository
import ir.mahdi.gharooni.gallerykt.domain.use_case.GetImagesUseCase
import ir.mahdi.gharooni.gallerykt.utils.BASE_URL
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAPI(): GalleryAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GalleryAPI::class.java)
    }


    @Provides
    @Singleton
    fun provideImagesRepository(api: GalleryAPI): GalleryRepository {
        return GalleryRepositoryImpl(api)
    }


    @Provides
    @Singleton
    fun provideImagesUseCase(repository: GalleryRepository): GetImagesUseCase {
        return GetImagesUseCase(repository)
    }
}