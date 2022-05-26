package fr.leboncoin.albuminterview.ui.adapter.generic

import androidx.recyclerview.widget.DiffUtil

abstract class ViewBindingDiffUtilCallback<Item : ViewBindingAdapterItem> :
    DiffUtil.ItemCallback<Item>()