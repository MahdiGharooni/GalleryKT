package ir.mahdi.gharooni.gallerykt.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mahdi.gharooni.gallerykt.domain.model.Image
import ir.mahdi.gharooni.gallerykt.domain.pagination.Paginator
import ir.mahdi.gharooni.gallerykt.domain.pagination.SearchPaginator
import ir.mahdi.gharooni.gallerykt.domain.use_case.SearchUseCase
import ir.mahdi.gharooni.gallerykt.presentation.state.GetImagesState
import ir.mahdi.gharooni.gallerykt.utils.Response
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: SearchUseCase
) : ViewModel() {
    private val _mutableState =
        mutableStateOf(GetImagesState(initialLoading = false, message = "Nothing To Show"))
    val state: State<GetImagesState> get() = _mutableState

    private var query: String = ""

    private var searchJob: Job? = null

    private lateinit var paginator: Paginator<Int, Image>

    fun startSearch(word: String) {
        query = word
        searchJob?.cancel()

        if (word.isNotEmpty()) {
            state.value.initialLoading = true
            state.value.images = emptyList()

            paginator = SearchPaginator(
                query = query,
                initialKey = state.value.page,
                onLoadUpdated = {
                    _mutableState.value = state.value.copy(
                        isLoading = it
                    )
                },
                onRequest = { nextPage, query ->
                    searchImages(nextPage, query)
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

            searchJob = viewModelScope.launch {
                delay(500L)
                paginator.loadNextItems()
            }
        } else {
            state.value.images = emptyList()
            state.value.message = "Nothing To Show"
        }
    }

    fun loadNextPage() {
        searchJob = viewModelScope.launch {
            paginator.loadNextItems()
        }
    }


    private suspend fun searchImages(page: Int, query: String): Response<List<Image>> {
        var res: Response<List<Image>>? = null
        useCase(page, query).collect {
            res = it
        }
        return res ?: Response.Loading()

    }
}