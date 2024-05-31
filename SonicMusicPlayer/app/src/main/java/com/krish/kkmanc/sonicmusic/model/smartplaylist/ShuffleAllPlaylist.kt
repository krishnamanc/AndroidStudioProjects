package com.krish.kkmanc.sonicmusic.model.smartplaylist

import com.krish.kkmanc.sonicmusic.App
import com.krish.kkmanc.sonicmusic.R
import com.krish.kkmanc.sonicmusic.model.Song
import kotlinx.parcelize.Parcelize

@Parcelize
class ShuffleAllPlaylist : AbsSmartPlaylist(
    name = App.getContext().getString(R.string.action_shuffle_all),
    iconRes = R.drawable.ic_shuffle
) {
    override fun songs(): List<Song> {
        return songRepository.songs()
    }
}