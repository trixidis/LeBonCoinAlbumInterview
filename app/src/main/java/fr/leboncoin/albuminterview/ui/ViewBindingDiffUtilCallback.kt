package fr.leboncoin.albuminterview.ui

import androidx.recyclerview.widget.DiffUtil

abstract class ViewBindingDiffUtilCallback<Item : ViewBindingAdapterItem> :
    DiffUtil.ItemCallback<Item>()