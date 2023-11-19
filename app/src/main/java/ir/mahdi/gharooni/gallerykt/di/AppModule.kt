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
import retrofit2.Retrofit
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class AppModule {



    @Provides
    @Singleton
    fun provideAPI(): GalleryAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
            .create(GalleryAPI::class.java)
    }


    @Provides
    @Singleton
    fun provideImagesRepository(api :GalleryAPI): GalleryRepository {
        return GalleryRepositoryImpl(api)
    }


    @Provides
    @Singleton
    fun provideImagesUseCase(repository : GalleryRepository): GetImagesUseCase {
        return GetImagesUseCase(repository)
    }
}