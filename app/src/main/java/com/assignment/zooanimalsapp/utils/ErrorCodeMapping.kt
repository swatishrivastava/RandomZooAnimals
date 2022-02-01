package com.assignment.zooanimalsapp.utils

import android.content.Context
import com.assignment.zooanimalsapp.R

fun getErrorMessageFromCode(context: Context, code: Int): String {
    return when (code) {
        500 -> context.getString(R.string.error_message_500)
        404 -> context.getString(R.string.error_message_400)
        else -> {
            context.getString(R.string.error_message_any)
        }
    }
}