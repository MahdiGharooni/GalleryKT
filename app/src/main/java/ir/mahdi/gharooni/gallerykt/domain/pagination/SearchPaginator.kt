package ir.mahdi.gharooni.gallerykt.domain.pagination

import ir.mahdi.gharooni.gallerykt.utils.Response

class SearchPaginator<Key, Image>(
    private val initialKey: Key,
    private val query: String,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key, query:String) -> Response<List<Image>>,
    private inline val getNextKey: suspend (List<Image>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<Image>, newKey: Key) -> Unit
) : Paginator<Key, Image> {
    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey, query)
        isMakingRequest = false

        when (result) {
            is Response.Loading<*> -> {
                onLoadUpdated(true)
            }

            is Response.Error<*> -> {
                onLoadUpdated(false)
                onError(Throwable(result.message))
            }

            is Response.Success<*> -> {
                onLoadUpdated(false)
                val images: List<Image> = result.data ?: emptyList()
                currentKey = getNextKey(images)
                onSuccess(images, currentKey)
            }

        }

    }

    override fun reset() {
        currentKey = initialKey
    }
}