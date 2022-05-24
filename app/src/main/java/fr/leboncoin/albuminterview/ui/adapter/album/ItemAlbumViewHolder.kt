package fr.leboncoin.albuminterview.ui.adapter.album

import android.util.Log
import fr.leboncoin.albuminterview.databinding.ItemAlbumBinding
import fr.leboncoin.albuminterview.ui.adapter.generic.ViewBindingViewHolder

class ItemAlbumViewHolder(binding: ItemAlbumBinding) : ViewBindingViewHolder<AlbumItem, ItemAlbumBinding>(
    binding
) {
    override fun bind(item: AlbumItem) {
        binding.textViewAlbumId.text = item.album.id.toString()
        binding.cardView.setOnClickListener {
            //navigate to album details
            Log.d("nav","on va naviguer aux tittres de l'album ${item.album.titles}")
        }
    }
}