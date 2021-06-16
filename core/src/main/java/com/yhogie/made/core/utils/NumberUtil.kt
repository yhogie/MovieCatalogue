package com.yhogie.made.core.utils

import android.content.Context
import com.yhogie.made.core.R
import java.text.NumberFormat
import java.util.*

object NumberUtil {

    fun formatNumber(number: Int, context: Context): String =
        String.format(
            context.getString(R.string.vote_count),
            NumberFormat.getNumberInstance(Locale.getDefault())
                .format(number)
        )
}