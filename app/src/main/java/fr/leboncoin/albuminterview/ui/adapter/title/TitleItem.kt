package fr.leboncoin.albuminterview.ui.adapter.title

import fr.leboncoin.albuminterview.ui.adapter.album.ItemAlbum
import fr.leboncoin.albuminterview.ui.adapter.generic.ViewBindingAdapterItem
import fr.leboncoin.presentation.model.AlbumUiModel
import fr.leboncoin.presentation.model.TitleUiModel

class TitleItem (
    val title: TitleUiModel
) : ViewBindingAdapterItem {
    override val itemViewType: Int
        get() = ItemTitle.TITLE_TYPE
}