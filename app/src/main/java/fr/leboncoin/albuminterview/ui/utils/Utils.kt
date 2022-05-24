package fr.leboncoin.albuminterview.ui.utils

import android.content.Context
import android.util.DisplayMetrics




object Utils {

        fun calculateNoOfColumns(context: Context): Int {
            val displayMetrics: DisplayMetrics = context.getResources().getDisplayMetrics()
            val dpWidth = displayMetrics.widthPixels / displayMetrics.density
            // Where 180 is the width of your grid item. You can change it as per your convention.
            return (dpWidth / 180).toInt()
        }

}