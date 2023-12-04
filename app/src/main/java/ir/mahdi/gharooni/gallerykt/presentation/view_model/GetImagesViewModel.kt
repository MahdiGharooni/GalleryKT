package ir.mahdi.gharooni.gallerykt.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mahdi.gharooni.gallerykt.domain.model.Image
import ir.mahdi.gharooni.gallerykt.domain.pagination.GalleryPaginator
import ir.mahdi.gharooni.gallerykt.domain.use_case.GetImagesUseCase
import ir.mahdi.gharooni.gallerykt.presentation.state.GetImagesState
import ir.mahdi.gharooni.gallerykt.utils.Response
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetImagesViewModel @Inject constructor(
    private val useCase: GetImagesUseCase
) : ViewModel() {
    private val _mutableState = mutableStateOf(GetImagesState(initialLoading = true))
    val state: State<GetImagesState> get() = _mutableState

    private val paginator = GalleryPaginator(
        initialKey = state.value.page,
        onLoadUpdated = {
            _mutableState.value = state.value.copy(
                isLoading = it
            )
        },
        onRequest = { nextPage ->
            getImages(nextPage)
        },
        getNextKey = {
            _mutableState.value.page + 1
        },
        onError = {
            _mutableState.value = state.value.copy(
                initialLoading = false,
                message = it?.localizedMessage!!
            )
        },
        onSuccess = { items, newKey ->
            _mutableState.value = state.value.copy(
                initialLoading = false,
                images = state.value.images + items,
                page = newKey,
                endReached = items.isEmpty()
            )

        }
    )

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    init {
        loadNextItems()
    }


    private suspend fun getImages(page: Int): Response<List<Image>> {
        var res: Response<List<Image>>? = null
        useCase(page).collect {
            res = it
        }
        return res ?: Response.Loading()

    }




}