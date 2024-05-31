package com.krish.kkmanc.sonicmusic.extensions

import androidx.core.view.WindowInsetsCompat
import com.krish.kkmanc.sonicmusic.util.PreferenceUtil
import com.krish.kkmanc.sonicmusic.util.RetroUtil

fun WindowInsetsCompat?.getBottomInsets(): Int {
    return if (PreferenceUtil.isFullScreenMode) {
        return 0
    } else {
        this?.getInsets(WindowInsetsCompat.Type.systemBars())?.bottom ?: RetroUtil.navigationBarHeight
    }
}
