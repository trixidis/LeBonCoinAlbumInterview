package fr.leboncoin.albuminterview.ui.adapter.title

import fr.leboncoin.albuminterview.ui.adapter.album.AlbumItem
import fr.leboncoin.albuminterview.ui.adapter.generic.ViewBindingDiffUtilCallback

object TitleItemDiffCallback : ViewBindingDiffUtilCallback<TitleItem>() {

    override fun areItemsTheSame(oldItem: TitleItem, newItem: TitleItem): Boolean =
        oldItem.title.id == newItem.title.id


    override fun areContentsTheSame(oldItem: TitleItem, newItem: TitleItem): Boolean =
        oldItem.title.id == newItem.title.id

}