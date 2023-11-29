package ir.mahdi.gharooni.gallerykt.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.mahdi.gharooni.gallerykt.data.local.converters.Converters
import ir.mahdi.gharooni.gallerykt.data.local.db.GalleryDataBase
import ir.mahdi.gharooni.gallerykt.data.remote.GalleryAPI
import ir.mahdi.gharooni.gallerykt.data.repository.GalleryRepositoryImpl
import ir.mahdi.gharooni.gallerykt.domain.repository.GalleryRepository
import ir.mahdi.gharooni.gallerykt.domain.use_case.GetImagesUseCase
import ir.mahdi.gharooni.gallerykt.utils.BASE_URL
import ir.mahdi.gharooni.gallerykt.utils.DATABASE_NAME
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
    fun provideDatabase(app: Application): GalleryDataBase {
        return Room.databaseBuilder(
            app, GalleryDataBase::class.java, DATABASE_NAME
        ).addTypeConverter(Converters()).build()
    }


    @Provides
    @Singleton
    fun provideImagesRepository(api: GalleryAPI, db: GalleryDataBase): GalleryRepository {
        return GalleryRepositoryImpl(api, db.dao())
    }


    @Provides
    @Singleton
    fun provideImagesUseCase(repository: GalleryRepository): GetImagesUseCase {
        return GetImagesUseCase(repository)
    }
}