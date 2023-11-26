package ir.mahdi.gharooni.gallerykt.domain.pagination

interface Paginator<Key, Image> {
    suspend fun loadNextItems()
    fun reset()
}