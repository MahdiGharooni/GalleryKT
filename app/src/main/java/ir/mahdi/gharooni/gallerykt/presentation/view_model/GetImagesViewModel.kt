package ir.mahdi.gharooni.gallerykt.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mahdi.gharooni.gallerykt.domain.use_case.GetImagesUseCase
import ir.mahdi.gharooni.gallerykt.presentation.state.GetImagesState
import ir.mahdi.gharooni.gallerykt.utils.Response
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetImagesViewModel @Inject constructor(
    private val useCase: GetImagesUseCase
) : ViewModel() {

    init {
        getImages()
    }


    private val _state = mutableStateOf(GetImagesState())
    val state: State<GetImagesState> = _state


    private fun getImages() {
        viewModelScope.launch {
            useCase().collect {
                when (it) {
                    is Response.Loading<*> -> {
                        _state.value = GetImagesState(
                            isLoading = true,
                        )
                    }

                    is Response.Error<*> -> {
                        _state.value = GetImagesState(
                            isLoading = false,
                            message = it.message ?: "something is wrong"
                        )
                    }

                    is Response.Success<*> -> {
                        _state.value = GetImagesState(
                            isLoading = false,
                            image = it.data ?: emptyList()
                        )
                    }

                }
            }
        }
    }


}