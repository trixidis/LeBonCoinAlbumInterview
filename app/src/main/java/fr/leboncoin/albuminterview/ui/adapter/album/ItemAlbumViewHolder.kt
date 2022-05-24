package fr.leboncoin.albuminterview.ui

import fr.leboncoin.albuminterview.databinding.ItemAlbumBinding

class ItemAlbumViewHolder(binding: ItemAlbumBinding) : ViewBindingViewHolder<AlbumItem, ItemAlbumBinding>(
    binding
) {
    override fun bind(item: AlbumItem) {
        binding.textViewAlbumId.text = item.id.toString()
    }
}