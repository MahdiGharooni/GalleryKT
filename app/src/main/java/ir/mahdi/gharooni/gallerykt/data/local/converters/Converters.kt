package ir.mahdi.gharooni.gallerykt.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import ir.mahdi.gharooni.gallerykt.domain.model.Url
import ir.mahdi.gharooni.gallerykt.domain.model.User

@ProvidedTypeConverter
class Converters {
    @TypeConverter
    fun fromUser(user: User): String {
        return Gson().toJson(user)
    }

    @TypeConverter
    fun toUser(userJson: String): User {
        return Gson().fromJson(userJson, User::class.java)
    }

    @TypeConverter
    fun fromUrl(url: Url): String {
        return Gson().toJson(url)
    }

    @TypeConverter
    fun toUrl(urlJson: String): Url {
        return Gson().fromJson(urlJson, Url::class.java)
    }
}