package com.krish.kkmanc.sonicmusic.util.theme

import android.content.Context
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDelegate
import com.krish.kkmanc.sonicmusic.R
import com.krish.kkmanc.sonicmusic.extensions.generalThemeValue
import com.krish.kkmanc.sonicmusic.util.PreferenceUtil
import com.krish.kkmanc.sonicmusic.util.theme.ThemeMode.*

@StyleRes
fun Context.getThemeResValue(): Int =
    if (PreferenceUtil.materialYou) {
        if (generalThemeValue == BLACK) R.style.Theme_SonicMusic_MD3_Black
        else R.style.Theme_SonicMusic_MD3
    } else {
        when (generalThemeValue) {
            LIGHT -> R.style.Theme_SonicMusic_Light
            DARK -> R.style.Theme_SonicMusic_Base
            BLACK -> R.style.Theme_SonicMusic_Black
            AUTO -> R.style.Theme_SonicMusic_FollowSystem
        }
    }

fun Context.getNightMode(): Int = when (generalThemeValue) {
    LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
    DARK -> AppCompatDelegate.MODE_NIGHT_YES
    else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
}