package fr.leboncoin.albuminterview.ui.adapter.album

import fr.leboncoin.albuminterview.ui.adapter.generic.ViewBindingDiffUtilCallback

object AlbumItemDiffCallback : ViewBindingDiffUtilCallback<AlbumItem>() {

    override fun areItemsTheSame(oldItem: AlbumItem, newItem: AlbumItem): Boolean =
        oldItem.album.id == newItem.album.id

    override fun areContentsTheSame(oldItem: AlbumItem, newItem: AlbumItem): Boolean =
        oldItem.album.id == newItem.album.id
}