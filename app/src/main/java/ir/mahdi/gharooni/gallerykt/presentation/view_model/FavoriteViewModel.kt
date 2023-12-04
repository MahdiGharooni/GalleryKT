package ir.mahdi.gharooni.gallerykt.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mahdi.gharooni.gallerykt.domain.model.Image
import ir.mahdi.gharooni.gallerykt.domain.use_case.FavoriteUseCase
import ir.mahdi.gharooni.gallerykt.presentation.state.FavoriteState
import ir.mahdi.gharooni.gallerykt.utils.DO_NOT_HAVE_FAV
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val useCase: FavoriteUseCase
) : ViewModel() {

//    private val _mutableState = mutableStateOf(FavoriteState())
//    val state: State<FavoriteState> get() = _mutableState

    private val _mutableState = MutableStateFlow(FavoriteState())
    val state = _mutableState.asStateFlow()


    init {
        println("viewmodelllllll: init")

        viewModelScope.launch {
            getFavoriteImages()
        }
    }

    fun insertFavoriteImage(image: Image) {
        viewModelScope.launch {
            _mutableState.value = _mutableState.value.copy(
                images = _mutableState.value.images + image
            )
            useCase.insertImage(image)
        }
    }


      fun getFavoriteImages() {
          println("viewmodelllllll: getimages")
          viewModelScope.launch {
              _mutableState.value = _mutableState.value.copy(isLoading = true)
              val images: List<Image> = useCase.getFavoriteImages()
              if (images.isNotEmpty()) {
                  _mutableState.value = _mutableState.value.copy(
                      isLoading = false, images = images,
                  )
              } else {
                  _mutableState.value = _mutableState.value.copy(
                      isLoading = false, message = DO_NOT_HAVE_FAV, images = emptyList(),
                  )
              }
          }

    }

    fun deleteFavoriteImage(image: Image) {
        viewModelScope.launch {
            if (state.value.images.contains(image)) {
                val newImages: List<Image> = state.value.images - image
                var message = ""
                if (newImages.isEmpty()) {
                    message = DO_NOT_HAVE_FAV
                }
                _mutableState.value =
                    state.value.copy(images = newImages, message = message)
            }
            useCase.deleteImage(image)
        }
    }


    fun isFavorite(image: Image): Boolean {
        return _mutableState.value.images.contains(image)
    }
}