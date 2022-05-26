package fr.leboncoin.albuminterview.ui.adapter.title

import android.view.ViewGroup
import fr.leboncoin.albuminterview.databinding.ItemTitleBinding
import fr.leboncoin.albuminterview.ui.adapter.generic.ViewBindingAdapter
import fr.leboncoin.albuminterview.ui.adapter.generic.ViewBindingViewHolder
import fr.leboncoin.albuminterview.ui.adapter.title.ItemTitle.TitleItem.TITLE_TYPE

class TitleAdapter : ViewBindingAdapter<TitleItem, ItemTitleBinding>(
   TitleItemDiffCallback
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingViewHolder<TitleItem, ItemTitleBinding> {

        val inflater = parent.layoutInflater
        return when (viewType) {
            TITLE_TYPE -> {
                val binding = ItemTitleBinding.inflate(inflater, parent, false)
                ItemTitleViewHolder(binding)
            }
            else -> throw IllegalStateException("Unknown viewType: $viewType")
        }
    }
}