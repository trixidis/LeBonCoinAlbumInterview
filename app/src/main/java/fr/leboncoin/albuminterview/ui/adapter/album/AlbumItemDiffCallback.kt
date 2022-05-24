package fr.leboncoin.albuminterview.ui

object AlbumItemDiffCallback : ViewBindingDiffUtilCallback<AlbumItem>() {

    override fun areItemsTheSame(oldItem: AlbumItem, newItem: AlbumItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: AlbumItem, newItem: AlbumItem): Boolean =
        oldItem.id == newItem.id
}