package fr.leboncoin.albuminterview.ui.adapter.album

import fr.leboncoin.albuminterview.ui.adapter.generic.ViewBindingAdapterItem
import fr.leboncoin.presentation.model.AlbumUiModel

class AlbumItem(
    val album: AlbumUiModel
) : ViewBindingAdapterItem {
    override val itemViewType: Int
        get() = ItemAlbum.ALBUM_TYPE
}