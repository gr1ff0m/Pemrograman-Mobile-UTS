package com.edumate.utils

import android.app.Activity
import android.content.Context

fun exitApp(context: Context) {
    if (context is Activity) {
        context.finish()
    }
}
