package com.krish.kkmanc.sonicmusic.model.smartplaylist

import androidx.annotation.DrawableRes
import com.krish.kkmanc.sonicmusic.R
import com.krish.kkmanc.sonicmusic.model.AbsCustomPlaylist

abstract class AbsSmartPlaylist(
    name: String,
    @DrawableRes val iconRes: Int = R.drawable.ic_queue_music
) : AbsCustomPlaylist(
    id = PlaylistIdGenerator(name, iconRes),
    name = name
)