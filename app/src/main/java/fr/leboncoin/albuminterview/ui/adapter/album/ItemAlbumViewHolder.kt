package fr.leboncoin.albuminterview.ui.adapter.album

import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import fr.leboncoin.albuminterview.R
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
        val url = GlideUrl(
            item.album.titles.first().url, LazyHeaders.Builder()
                .addHeader("User-Agent", "Android")
                .build()
        )
        Glide
            .with(binding.root)
            .load(url)
            .placeholder(R.drawable.loading)
            .into(binding.imageView)

    }
}