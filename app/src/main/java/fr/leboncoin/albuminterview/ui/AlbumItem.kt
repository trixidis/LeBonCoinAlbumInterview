package fr.leboncoin.albuminterview.ui

class AlbumItem(
    val id: Int
) : ViewBindingAdapterItem {
    override val itemViewType: Int
        get() = ItemAlbum.ALBUM_TYPE
}