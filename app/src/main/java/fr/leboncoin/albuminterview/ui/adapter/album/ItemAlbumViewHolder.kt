package fr.leboncoin.albuminterview.ui.adapter.album

import android.util.Log
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import fr.leboncoin.albuminterview.R
import fr.leboncoin.albuminterview.databinding.ItemAlbumBinding
import fr.leboncoin.albuminterview.ui.adapter.generic.ViewBindingViewHolder
import fr.leboncoin.albuminterview.ui.fragments.ListAlbumsFragmentDirections
import fr.leboncoin.albuminterview.ui.fragments.ListTitlesFragment
import fr.leboncoin.albuminterview.ui.fragments.ListTitlesFragmentArgs
import fr.leboncoin.albuminterview.ui.utils.Utils


class ItemAlbumViewHolder(binding: ItemAlbumBinding,val listener : OnAlbumClickListener) : ViewBindingViewHolder<AlbumItem, ItemAlbumBinding>(
    binding
) {
    override fun bind(item: AlbumItem) {
        binding.textViewAlbumId.text = item.album.id.toString()
        binding.cardView.setOnClickListener {
            //navigate to album details
            listener.onclick(item.album.id)
        }

        Glide
            .with(binding.root)
            .load(Utils.getGlideUrl(item.album.titles.first().url))
            .placeholder(R.drawable.loading)
            .into(binding.imageView)

    }
}