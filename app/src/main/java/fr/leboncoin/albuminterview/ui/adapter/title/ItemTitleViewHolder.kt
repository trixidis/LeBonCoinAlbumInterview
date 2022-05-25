package fr.leboncoin.albuminterview.ui.adapter.title

import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import fr.leboncoin.albuminterview.R
import fr.leboncoin.albuminterview.databinding.ItemAlbumBinding
import fr.leboncoin.albuminterview.databinding.ItemTitleBinding
import fr.leboncoin.albuminterview.ui.adapter.album.AlbumItem
import fr.leboncoin.albuminterview.ui.adapter.generic.ViewBindingViewHolder

class ItemTitleViewHolder(binding: ItemTitleBinding) :
    ViewBindingViewHolder<TitleItem, ItemTitleBinding>(
        binding
    ) {

    override fun bind(item: TitleItem) {
        binding.textViewTitle.text = item.title.name
        binding.imageViewTitle
        val url = GlideUrl(
            item.title.url, LazyHeaders.Builder()
                .addHeader("User-Agent", "Android")
                .build()
        )
        Glide
            .with(binding.root)
            .load(url)
            .placeholder(R.drawable.loading)
            .into(binding.imageViewTitle)
    }
}