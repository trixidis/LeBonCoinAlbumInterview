package fr.leboncoin.albuminterview.ui.adapter.album

import android.view.ViewGroup
import fr.leboncoin.albuminterview.databinding.ItemAlbumBinding
import fr.leboncoin.albuminterview.ui.adapter.generic.ViewBindingAdapter
import fr.leboncoin.albuminterview.ui.adapter.generic.ViewBindingViewHolder

class AlbumAdapter : ViewBindingAdapter<AlbumItem, ItemAlbumBinding>(AlbumItemDiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingViewHolder<AlbumItem, ItemAlbumBinding> {

        val inflater = parent.layoutInflater
        return when (viewType) {
            ItemAlbum.ALBUM_TYPE -> {
                val binding = ItemAlbumBinding.inflate(inflater, parent, false)
                ItemAlbumViewHolder(binding)
            }
            else -> throw IllegalStateException("Unknown viewType: $viewType")
        }    }
}