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
import fr.leboncoin.albuminterview.ui.utils.Utils

class ItemTitleViewHolder(binding: ItemTitleBinding) :
    ViewBindingViewHolder<TitleItem, ItemTitleBinding>(
        binding
    ) {

    override fun bind(item: TitleItem) {
        binding.textViewTitle.text = "${item.title.id} \n ${item.title.name}"
        binding.imageViewTitle
        Glide
            .with(binding.root)
            .load(Utils.getGlideUrl(item.title.url))
            .placeholder(R.drawable.loading)
            .into(binding.imageViewTitle)
    }
}